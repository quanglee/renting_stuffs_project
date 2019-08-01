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
        WHERE b.borrowerId = '${userId}' ORDER BY b.id DESC;
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
        return db.execute(`SELECT * FROM bookings WHERE itemId IN (SELECT id FROM items WHERE ownerId = '${userId}') ORDER BY id DESC;`);
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

    //delete a booking
    static deleteBooking(params){

        return db.execute(
            'DELETE FROM bookings WHERE id = ?;',
            [
                params.id
            ]
        );
    }
    
    //update a booking
    static updateBooking(params){

        return db.execute(
            'UPDATE bookings SET status = ? WHERE id = ?;',
            [
                params.status,
                params.id
            ]
        );
    }
}

// SELECT * FROM bookings WHERE borrowerId = 'user6@shareandget.com';
// SELECT id FROM items WHERE ownerId = 'user1@shareandget.com';
// SELECT * FROM bookings WHERE itemId IN (SELECT id FROM items WHERE ownerId = 'user1@shareandget.com');
