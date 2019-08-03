const db = require("../util/database");

module.exports = class Review {
    constructor() {
        // any
    }

    //add a review for item
    static addReview(params){
        
        return db.execute(
            'INSERT INTO reviews (itemId, borrowerId, title, content, rating, bookingId) VALUES (?, ?, ?, ?, ?, ?)',
            [
                params.itemId, params.borrowerId, params.title, params.content, params.rating, params.bookingId
            ]
        );
    }

    // find all reviews of an item
    static findAllReviewsOfItem(itemId) {
        return db.execute(
            `SELECT r.id, r.bookingId, r.borrowerId, r.title, r.content, r.rating
                , b.startDate, b.returnDate
                FROM reviews r
                    INNER JOIN bookings b ON r.bookingId = b.id
                
                WHERE r.itemId = '${itemId}' ORDER BY r.id DESC;`);
    }
}