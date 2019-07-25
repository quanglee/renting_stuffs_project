// place the users logic here
const User = require('../models/user');
const Item = require('../models/item');
const { firebaseAdmin } = require('../util/firebase');

exports.getAllUsers = (req, res, next) => {
    console.log("get all users from mysql and return JSON file");
    // we use promise which is nicer than callback
    User.findAll()
        .then(([rows, fields]) => {
            res.status(200).json({
                users: rows
            })
        }).catch(err => {
            console.log(err);
        });
};

exports.create = (req, res, next) => {
  console.log("register user");
  if (!req.body.email || 
    !req.body.password || 
    !req.body.username) {
    return res.status(400).send({
      success: 'false',
      message: 'email, password, username are required',
    });
  }
  const email = req.body.email;
  const password = req.body.password;
  const username = req.body.username;
  const lat = req.body.lat != undefined ? req.body.lat : null;
  const lng = req.body.lng != undefined ? req.body.lng : null;
  firebaseAdmin.auth().createUser({
    email: email,
    password: password,
    displayName: username
  }).then(userRecord => {
    const user = {
      email: email,
      username: username,
      isAdmin: false,
      lat: lat,
      lng: lng,
      isActive: true
    };
    User.saveUser(user).then(resultData => {
      user.uid = userRecord.uid;
      res.status(201).json(user);
    }).catch(err => {
      res.status(400).json({
        message: "unable to create record"
      });
    });
  }).catch(err => {
    let status = 400;
    if (err.errorInfo.code == 'auth/email-already-exists')
      status = 409;
    res.status(status).json({
      message: err.errorInfo.message
    });
  });
};

exports.getAllItemsOfUser = (req, res, next) => {
  // we use promise which is nicer than callback
  Item.findAllItemsOfUser(req.params.ownerId)
      .then(([rows, fields]) => {
          res.status(200).json(rows);
      }).catch(err => {
          console.log(err);
      });
};
