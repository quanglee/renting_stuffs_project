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
    DB_CONNECT_LIMIT: process.env.DB_CONNECT_LIMIT,
    FIREBASE_SERVICE_ACCOUNT_JSON: process.env.FIREBASE_SERVICE_ACCOUNT_JSON,
    FIREBASE_DATABASE_URL: process.env.FIREBASE_DATABASE_URL,
    FIREBASE_API_KEY: process.env.FIREBASE_API_KEY,
    FIREBASE_AUTH_DOMAIN: process.env.FIREBASE_AUTH_DOMAIN,
    FIRE_BASE_PROJECT_ID: process.env.FIRE_BASE_PROJECT_ID,
    FIREBASE_STORAGEBUCKET: process.env.FIREBASE_STORAGEBUCKET,
    FIREBASE_MESSAGING_SENDER_ID: process.env.FIREBASE_MESSAGING_SENDER_ID,
    FIREBASE_APP_ID: process.env.FIREBASE_APP_ID,
    UPLOAD_IMAGE_FOLDER: process.env.UPLOAD_IMAGE_FOLDER,
} 