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
            `SELECT r.* FROM reviews r
            WHERE r.itemId = '${itemId}' ORDER BY r.id DESC;`);
    }
}