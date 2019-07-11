'use strict';
const express = require('express');
const bodyParser = require('body-parser');

// TODO: Constants will be replaced by env file later
const PORT = 3000;
const HOST = '0.0.0.0';

// App
const app = express();
const userRoutes = require('./routes/user');
app.use(bodyParser.urlencoded({extended: false}));
app.use(bodyParser.json());

// Place your main routers here
app.use('/users', userRoutes);
// ... //

app.use((req, res, next) => {
    res.status(404).send("<h1>Page not found 404</h1>")
});

app.listen(PORT, HOST);
console.log(`Running on http://${HOST}:${PORT}`);