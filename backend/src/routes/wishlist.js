const express = require('express');
const routers = express.Router();
const isAuth = require('../util/auth');
const wishlistController = require('../controllers/wishlists');
// ALL ROUTES OF WISHLIST
// prefix: /wishlists

routers.post('/create', isAuth, wishlistController.create);

module.exports = routers;
