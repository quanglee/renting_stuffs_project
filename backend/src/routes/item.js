const express = require('express');
const routers = express.Router();
const isAuth = require('../util/auth');
const itemController = require('../controllers/items');
// ALL ROUTES OF WISHLIST
// prefix: /items

routers.get('/', isAuth, itemController.getAllItems);
routers.get('/:item_id', isAuth, itemController.getItemDetail);
module.exports = routers;