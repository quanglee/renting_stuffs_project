// place the wishlists logic here
const Wishlist = require('../models/wishlist');

exports.getAllWishlists = (req, res, next) => {
    console.log("get all list of items from wishlist from mysql and return JSON file");
    // we use promise which is nicer than callback
    Wishlist.findAll()
        .then(([rows, fields]) => {
            res.status(200).json({
                wishlists: rows
            })
        }).catch(err => {
            console.log(err);
        });
};

exports.getWishlistsOfUser = (req, res, next) => {
    console.log("get all list of items from wishlist of a user from mysql and return JSON file");
    // we use promise which is nicer than callback
    Wishlist.findWishlistsOfUser(req.params.userId)
        .then(([rows, fields]) => {
            res.status(200).json({
                wishlists: rows
            })
        }).catch(err => {
            console.log(err);
        });
};