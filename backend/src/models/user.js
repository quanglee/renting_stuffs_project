const db = require("../util/database");
const { firebaseDB } = require('../util/firebase');

module.exports = class User {
    constructor() {
        // any
    }

    static findAll() {
        return db.execute('SELECT * FROM users;');
    }

    static findUserByEmail(email) {

    }

    static saveUser(...data) {
        const userDoc = data[0];
        return firebaseDB.collection("users")
                                    .doc(userDoc.email)
                                    .set(userDoc);
    }
}