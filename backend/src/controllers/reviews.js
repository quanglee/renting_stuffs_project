// place the booking logic here
const Review = require('../models/review');
const Item = require('../models/item');

exports.addReview = (req, res, next) => {
    if (req.user == null) {
      res.status(400).json({
        message: "The user should be provided, add the callback to the router to check if the user is logged"
      });
      return;
    }else if (!req.body.itemId || 
        !req.body.borrowerId || 
        !req.body.title ||
        !req.body.rating) {
        return res.status(400).send({
            success: 'false',
            message: 'itemId, borrowerId, title and rating are required',
        });
    }

    //get the item
    Item.findByItemId(req.body.itemId)
        .then(([rows, fields]) => {
            
            if(rows[0] != undefined){

                //update rating stats
                let currentReviews = rows[0].numberOfReview
                let currentAverageRating = rows[0].averageRating;
                rows[0].numberOfReview =  currentReviews + 1;
                rows[0].averageRating =  ((currentReviews * currentAverageRating)
                                    + req.body.rating) / rows[0].numberOfReview;

                Item.editItem(rows[0]).then(([rows, fields]) => {

                    //add new review
                    Review.addReview(req.body)
                    .then(([rows, fields]) => {
                        res.status(200).json(rows)
                    }).catch(err => {
                        console.log(err);
                    });

                }).catch(err => {
                console.log(err);
                });

            }else{
                //error
                console.log("Cannot find the item!");
            }
        }).catch(err => {
            console.log(err);
        });
};


  