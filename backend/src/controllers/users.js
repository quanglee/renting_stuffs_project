// place the users logic here
const User = require('../models/user');
const firebaseAdmin = require('../util/firebase');

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