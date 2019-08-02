// place the booking logic here
const Review = require('../models/review');

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
    
    Review.addReview(req.body)
        .then(([rows, fields]) => {
            res.status(200).json(rows)
        }).catch(err => {
            console.log(err);
        });
};


  