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

    //get all itemIds of user, then get all bookings that have itemIds
    static findAllRequestOfUser(userId) {
        return db.execute(`SELECT * FROM bookings WHERE itemId IN (SELECT id FROM items WHERE ownerId = '${userId}');`);
    }
    
}

// SELECT * FROM bookings WHERE borrowerId = 'user6@shareandget.com';
// SELECT id FROM items WHERE ownerId = 'user1@shareandget.com';
// SELECT * FROM bookings WHERE itemId IN (SELECT id FROM items WHERE ownerId = 'user1@shareandget.com');
