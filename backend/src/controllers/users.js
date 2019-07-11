// place the users logic here
const User = require('../models/user');

exports.getAllUsers = (req, res, next) => {
    console.log("get all users from mysql and return JSON file");
    const user = new User();
    res.send("<h5>Display list of users</h5>")
};