const express = require('express');
const router = express.Router();
const authController = require('../controllers/auth');
// ALL ROUTES OF USERS
// prefix: /users

router.get('/login', authController.loginUser);
router.post('/user', authController.registerUser);

module.exports = router;