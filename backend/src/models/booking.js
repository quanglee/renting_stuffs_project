const db = require("../util/database");

module.exports = class Booking {
    constructor() {
        // any
    }

    //get all bookings that have borrowerId = userId
    static findAllBookingOfUser(userId) {
        return db.execute(`
        SELECT b.*
        FROM bookings b 
        WHERE b.borrowerId = '${userId}';
        `);
    }

    static findPendingBookingOfItem(itemId){
        return db.execute(`
        SELECT b.*
        FROM bookings b 
        WHERE b.itemId = ${itemId} AND b.status = 'pending';
        `);
    }

    static findAllAcceptedBookingOfItem(itemId){
        return db.execute(`
        SELECT b.*
        FROM bookings b 
        WHERE b.itemId = ${itemId} AND b.status = 'Accepted';
        `);
    }

    //get all itemIds of user, then get all bookings that have itemIds
    static findAllRequestOfUser(userId) {
        return db.execute(`SELECT * FROM bookings WHERE itemId IN (SELECT id FROM items WHERE ownerId = '${userId}');`);
    }

    //add a booking
    static addBooking(params){

        return db.execute(
            'INSERT INTO bookings (itemId, borrowerId, status, startDate, returnDate) VALUES (?, ?, ?, ?, ?)',
            [
                params.itemId, params.borrowerId, params.status, params.startDate, params.returnDate
            ]
        );
    }
    
}

// SELECT * FROM bookings WHERE borrowerId = 'user6@shareandget.com';
// SELECT id FROM items WHERE ownerId = 'user1@shareandget.com';
// SELECT * FROM bookings WHERE itemId IN (SELECT id FROM items WHERE ownerId = 'user1@shareandget.com');
