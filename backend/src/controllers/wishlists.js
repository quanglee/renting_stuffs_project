// place the wishlists logic here
const Wishlist = require('../models/wishlist');
const Utils = require('../util/utils');
const FCM = require('../util/fcm');
const { firebaseAdmin } = require('../util/firebase');

//create wishlist item
exports.create = (req, res, next) => {
  console.log("Create wishlist");
  if (req.user == null) {
    res.status(400).json({
      message: "The user should be provided, add the callback to the router to check if the user is logged"
    });
    return;
  }

  req.body.ownerId = req.user.email;
  req.body.endDate = null;
  const fcmToken = req.body.fcmToken;
  Wishlist.create(req.body).then(([rows, fields]) => {
    // subscribe this item, in order to receive notification
    FCM.subscribeFromTopic(fcmToken, req.body.itemId);
    res.status(201).json(req.body);
  }).catch(err => {
    console.log(err);
    if (err.errno == 1062)
      res.status(409).json({message: err.message});
  });
};

//delete wishlist item
exports.delete = (req, res, next) => {
  console.log("Delete wishlist");
  if (req.user == null) {
    res.status(400).json({
      message: "The user should be provided, add the callback to the router to check if the user is logged"
    });
    return;
  }

  req.body.ownerId = req.user.email;
  const fcmToken = req.body.fcmToken;
  Wishlist.delete(req.body).then(([rows, fields]) => {
    // unsubribe this item topic
    FCM.unSubscribeFromTopic(fcmToken, req.body.itemId);
    res.status(201).json(req.body);
  }).catch(err => {
    console.log(err);
    if (err.errno == 1062)
      res.status(409).json({message: err.message});
  });
};
