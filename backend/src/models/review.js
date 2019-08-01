const db = require("../util/database");

module.exports = class Review {
    constructor() {
        // any
    }

    //get all reviews of a specific item
    static findAllReviewsOfItem(itemId) {
        return db.execute(`
        SELECT r.*
        FROM reviews r
        WHERE r.itemId = '${itemId}' ORDER BY r.id DESC;
        `);
    }

    //add a review for item
    static addReview(params){

        return db.execute(
            'INSERT INTO reviews (itemId, borrowerId, title, content, rating) VALUES (?, ?, ?, ?, ?)',
            [
                params.itemId, params.borrowerId, params.title, params.content, params.rating
            ]
        );
    }
}