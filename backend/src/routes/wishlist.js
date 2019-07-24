const express = require('express');
const routers = express.Router();
const isAuth = require('../util/auth');
const wishlistController = require('../controllers/wishlists');
// ALL ROUTES OF WISHLIST
// prefix: /wishlists

routers.get('/', wishlistController.getAllWishlists);
routers.get('/:userId', wishlistController.getWishlistsOfUser);

module.exports = routers;