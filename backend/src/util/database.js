const mysql = require('mysql2');

const connection = mysql.createPool({
    connectionLimit: 10,
    host: 'localhost',
    user: 'root',
    database: 'renting_db',
    password: 'root'
});

module.exports = connection.promise();