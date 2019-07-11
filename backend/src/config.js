const dotenv = require('dotenv');
dotenv.config();

// PLACE YOUR CONFIG VARIABLES HERE (.env file)
module.exports = {
    NODE_HOST: process.env.NODE_HOST,
    NODE_PORT: process.env.NODE_PORT,
    DB_HOST: process.env.DB_HOST,
    DB_USER: process.env.DB_USER,
    DB_PASS: process.env.DB_PASS,
    DB_DATABASE: process.env.DB_DATABASE,
    DB_CONNECT_LIMIT: process.env.DB_CONNECT_LIMIT
}