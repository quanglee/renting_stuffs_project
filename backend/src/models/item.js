const db = require("../util/database");

module.exports = class Items {
    constructor() {
        // any
    }

    static findAll() {
        return db.execute('SELECT * FROM items;');
    }
}