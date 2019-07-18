const express = require('express');
const routers = express.Router();
const isAuth = require('../util/auth');
const bookingController = require('../controllers/bookings');
// ALL ROUTES OF WISHLIST
// prefix: /bookings

routers.get('/:userId', isAuth, bookingController.getAllBookingOfUser);
routers.get('/requests/:userId', isAuth, bookingController.getAllRequestOfUser);

module.exports = routers;