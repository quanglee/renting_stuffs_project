const express = require('express');
const routers = express.Router();
const isAuth = require('../util/auth');
const itemController = require('../controllers/items');
const uploadImageService = require('../upload-file');
// ALL ROUTES OF WISHLIST
// prefix: /items

routers.get('/', itemController.getAllItems);
routers.get('/:item_id/reviews', itemController.getAllReviewsOfItem);
routers.get('/:item_id', isAuth, itemController.getItemDetail);
routers.post('/add', isAuth, uploadImageService.single('image'), itemController.createItem);
routers.put('/edit/:item_id', isAuth, uploadImageService.single('image'), itemController.editItem);
routers.delete('/delete/:item_id', isAuth, itemController.deleteItem);

module.exports = routers;
