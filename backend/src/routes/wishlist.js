const express = require('express');
const routers = express.Router();
const isAuth = require('../util/auth');
const wishlistController = require('../controllers/wishlists');
// ALL ROUTES OF WISHLIST
// prefix: /wishlists
routers.post('/create', isAuth, wishlistController.create);

routers.post('/delete', isAuth, wishlistController.delete);

module.exports = routers;
