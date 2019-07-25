const express = require('express');
const router = express.Router();
const usersController = require('../controllers/users');
const isAuth = require('../util/auth');
// ALL ROUTES OF USERS
// prefix: /users

router.get('/', isAuth, usersController.getAllUsers);
router.get('/:ownerId/items', isAuth, usersController.getAllItemsOfUser);
router.post('/create', usersController.create);

module.exports = router;
