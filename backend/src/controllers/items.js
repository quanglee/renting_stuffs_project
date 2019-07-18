// place the wishlists logic here
const Item = require('../models/item');
const { UPLOAD_IMAGE_FOLDER } = require('../config');

exports.getAllItems = (req, res, next) => {
    // we use promise which is nicer than callback
    Item.findAll()
        .then(([rows, fields]) => {
            res.status(200).json({
                items: rows
            })
        }).catch(err => {
            console.log(err);
        });
};

exports.getItemDetail = (req, res, next) => {
    Item.findByItemId(req.params.item_id)
        .then(([rows, fields]) => {
            res.status(200).json({
                item: rows
            })
        }).catch(err => {
            console.log(err);
        });
};

exports.createItem= (req, res, next) => {
    if (!req.file) {
        res.status(422).json({
            message: "missing an upload file"
        });
    }

    itemImageURL = UPLOAD_IMAGE_FOLDER + req.file.filename;
    Item.addItem({
        ownerId: req.loggedInEmail,
        name: req.body.name,
        description: req.body.description,
        condition: req.body.condition,
        category: req.body.category,
        imageURLs: itemImageURL,
        tags: req.body.tags,
        lat: req.body.lat,
        lng: req.body.lng,
        price: req.body.price
    }).then(([rows, fields]) => {
        res.status(201).json({
            message: "success",
            item: rows
        })
    }).catch(err => {
        console.log(err);
    });
};

exports.editItem= (req, res, next) => {
};

exports.deleteItem= (req, res, next) => {
};