// place the users logic here
const User = require('../models/user');

exports.getAllUsers = (req, res, next) => {
    console.log("get all users from mysql and return JSON file");
    // we use promise which is nicer than callback
    User.findAll()
        .then(([rows, fields]) => {
            res.send(JSON.stringify(rows));
        }).catch(err => {
            console.log(err);
        });
};