const db = require("../util/database");

module.exports = class Wishlist {
  constructor() {
    // any
  }

  static create(params) {
    return db.execute(
      `INSERT INTO wishlists (itemId, ownerId, endDate) 
      VALUES (?, ?, ?)`, [
        params.itemId, params.ownerId, params.endDate
      ]
    );
  }

}

// SELECT * FROM items WHERE id IN (1,2,3) AND isActive = 1;
// SELECT itemId FROM wishlists WHERE ownerId = 'user5@shareandget.com' AND endDate >= CURDATE();
// SELECT * FROM items WHERE id IN (
//    SELECT itemId FROM wishlists WHERE ownerId = 'user5@shareandget.com' AND endDate >= CURDATE()
// ) AND isActive = 1;
