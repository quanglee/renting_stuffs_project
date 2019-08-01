const express = require('express');
const routers = express.Router();
const isAuth = require('../util/auth');
const reviewController = require('../controllers/reviews');
// ALL ROUTES OF REVIEWS
// prefix: /reviews

// routers.get('/', isAuth, reviewController.getAllBookingOfUser);
routers.post('/create', isAuth, reviewController.addReview);

module.exports = routers;