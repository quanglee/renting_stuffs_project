// place the wishlists logic here
const Wishlist = require('../models/wishlist');
const Utils = require('../util/utils');

exports.create = (req, res, next) => {
  console.log("Create wishlist");
  if (req.user == null) {
    res.status(400).json({
      message: "The user should be provided, add the callback to the router to check if the user is logged"
    });
    return;
  }

  req.body.ownerId = req.user.email;
  Wishlist.create(req.body).then(([rows, fields]) => {
    res.status(201).json(req.body);
  }).catch(err => {
    console.log(err);
    if (err.errno == 1062)
      res.status(409).json({message: err.message});
  });
};
