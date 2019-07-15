// 'use strict';

const { NODE_HOST, NODE_PORT } = require('./config');
const express = require('express');
const bodyParser = require('body-parser');
const wishlistsRoutes = require('./routes/wishlist');

// App
const app = express();
const userRoutes = require('./routes/user');
const authRoutes = require('./routes/auth');
const wishlistRoutes = require('./routes/wishlist');

app.use(bodyParser.urlencoded({extended: false}));
app.use(bodyParser.json());

// Place your main routers here
app.use('/auth', authRoutes);
app.use('/users', userRoutes);
app.use('/wishlists', wishlistRoutes);
// ... //

app.use((req, res, next) => {
    res.status(404).send("<h1>Page not found 404</h1>")
});

app.listen(NODE_PORT, NODE_HOST);
console.log(`Running on http://${NODE_HOST}:${NODE_PORT}`);