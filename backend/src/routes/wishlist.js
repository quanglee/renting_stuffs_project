const express = require('express');
const routers = express.Router();
const isAuth = require('../util/auth');
const wishlistController = require('../controllers/wishlists');
// ALL ROUTES OF WISHLIST
// prefix: /wishlists

routers.get('/', isAuth, wishlistController.getAllWishlists);
routers.get('/:userId', isAuth, wishlistController.getWishlistsOfUser);

module.exports = routers;