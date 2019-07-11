const express = require('express');
const router = express.Router();
const usersController = require('../controllers/users');
// ALL ROUTES OF USERS
// prefix: /users

router.get('/', usersController.getAllUsers);

module.exports = router;