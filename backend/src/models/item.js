const db = require("../util/database");

module.exports = class Items {
    constructor() {
        // any
    }

    static findAll() {
        return db.execute('SELECT * FROM items;');
    }

    static findByItemId(itemId) {
        return db.execute(
            `SELECT * FROM items i 
            WHERE i.id = ${itemId};`);
    }
}