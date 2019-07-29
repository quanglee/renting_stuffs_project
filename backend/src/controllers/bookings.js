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

exports.createBooking = (req, res, next) => {
    if (req.user == null) {
      res.status(400).json({
        message: "The user should be provided, add the callback to the router to check if the user is logged"
      });
      return;
    }else if (!req.body.itemId || 
        !req.body.startDate || 
        !req.body.returnDate) {
        return res.status(400).send({
            success: 'false',
            message: 'itemId, startDate and returnDate are required',
        });
    }

    //check is there any pending booking of itemId
   Booking.findPendingBookingOfItem(req.body.itemId).then(([rows, fields]) => {
        // res.status(200).json(rows[0]);

        //if that itemId have no 'Pending' booking
        if(rows[0] == undefined){
            //check time conflicts with all bookings of itemId that have status 'Accepted'
            Booking.findAllAcceptedBookingOfItem(req.body.itemId).then(([rows, fields]) => {
                
                let canAddBooking = true; 

                //if that itemId have one or some 'Accepted' bookings
                if(rows[0] != undefined){

                    console.log("BOOKING:")
                    console.log((req.body.startDate));
                    console.log((req.body.returnDate));

                    rows.forEach((currentValue, index, array) => {
                        //Note: Date.parse(req.body.startDate) return a number
                        let bookingStartDateInMiliseconds = Date.parse(req.body.startDate);
                        let bookingReturnDateInMiliseconds = Date.parse(req.body.returnDate);

                        console.log("ACCEPTED BOOKING:")
                        console.log((currentValue.startDate));
                        console.log((currentValue.returnDate));

                        if((currentValue.startDate.getTime() >= bookingStartDateInMiliseconds && currentValue.startDate.getTime() <= bookingReturnDateInMiliseconds) ||
                        (currentValue.returnDate.getTime() >= bookingStartDateInMiliseconds && currentValue.returnDate.getTime() <= bookingReturnDateInMiliseconds)
                        ){
                            canAddBooking = false;      
                        }
                    });
                }

                if(canAddBooking){
                    console.log("Create booking");

                    const booking = {
                        itemId: req.body.itemId,
                        borrowerId: req.user.email,
                        status: 'Pending',
                        startDate: req.body.startDate,
                        returnDate: req.body.returnDate
                    };

                    Booking.addBooking(booking).then(([rows, fields]) => {
                        res.status(201).json(booking)
                    }).catch(err => {
                        console.log(err);
                    });

                }else{//return 'booking time conflicts with accepted bookings' error here
                    res.status(401).json({
                        message: "booking time conflicts with accepted bookings"
                    });
                }

            }).catch(err => {
                console.log(err);
            });

        }else{//return 'item has pending booking' error here
            res.status(401).json({
                message: "item has pending booking"
            });
        }
    }).catch(err => {
        console.log(err);
    });
  };


  exports.deleteBooking = (req, res, next) => {
    console.log("borrower delete the booking");

    // we use promise which is nicer than callback
    Booking.deleteBooking(req.params)
        .then(([rows, fields]) => {
            res.status(200).json(rows)
        }).catch(err => {
            console.log(err);
        });
};