const db = require("../util/database");

module.exports = class User {
    constructor() {
        // any
    }

    static findAll() {
        return db.execute('SELECT * FROM users;');
    }

    static findUserByEmail(email) {

    }
}