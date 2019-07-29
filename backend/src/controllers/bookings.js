// place the booking logic here
const Booking = require('../models/booking');

exports.getAllBookingOfUser = (req, res, next) => {
    console.log("get all bookings that user were made from mysql and return JSON file");
    if (req.user == null) {
        res.status(400).json({
          message: "The user should be provided, add the callback to the router to check if the user is logged"
        });
        return;
      }

    // we use promise which is nicer than callback
    console.log(req.user.email);

    Booking.findAllBookingOfUser(req.user.email)
        .then(([rows, fields]) => {
            res.status(200).json(rows)
        }).catch(err => {
            console.log(err);
        });
};

exports.getAllRequestOfUser = (req, res, next) => {
    console.log("get all bookings that other users were made to the user's items from mysql and return JSON file");
    // we use promise which is nicer than callback
    Booking.findAllRequestOfUser(req.params.userId)
        .then(([rows, fields]) => {
            res.status(200).json({
                requests: rows
            })
        }).catch(err => {
            console.log(err);
        });
};