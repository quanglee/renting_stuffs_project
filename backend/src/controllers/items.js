// place the wishlists logic here
const Item = require('../models/item');
const { UPLOAD_IMAGE_FOLDER } = require('../config');
const Utils = require('../util/utils');

exports.getAllItems = (req, res, next) => {
  // we use promise which is nicer than callback
  Item.findAll()
    .then(([rows, fields]) => {
      rows.forEach((currentValue, index, array) => {
        Utils.toBoolean(currentValue, 'isActive');
        array[index] = currentValue;
      });
      res.status(200).json(rows);
    }).catch(err => {
      console.log(err);
    });
};

exports.getItemDetail = (req, res, next) => {
    Item.findByItemId(req.params.item_id)
        .then(([rows, fields]) => {
            rows.forEach((currentValue, index, array) => {
              Utils.toBoolean(currentValue, 'isActive');
              array[index] = currentValue;
            });
            res.status(200).json(rows[0]);
        }).catch(err => {
            console.log(err);
        });
};

exports.createItem = (req, res, next) => {
  console.log("Create item");
  if (!req.file) {
    console.log(req.files);
    res.status(422).json({
        message: "missing an upload file"
    });
    return;
  } else if (req.user == null) {
    res.status(400).json({
      message: "The user should be provided, add the callback to the router to check if the user is logged"
    });
    return;
  }

  req.body.ownerId = req.user.email;
  req.body.imageURLs = UPLOAD_IMAGE_FOLDER + req.file.filename;

  Item.addItem(req.body).then(([rows, fields]) => {
    req.body.id = rows.insertId;
    res.status(201).json(req.body);
  }).catch(err => {
    console.log(err);
  });
};

exports.editItem = (req, res, next) => {
  console.log("Edit item");
  if (req.user == null) {
    res.status(400).json({
      message: "The user should be provided, add the callback to the router to check if the user is logged"
    });
    return;
  }

  if (req.file != null) 
    req.body.imageURLs = UPLOAD_IMAGE_FOLDER + req.file.filename;

  Item.editItem(req.body).then(([rows, fields]) => {
    res.status(200).json(req.body);
  }).catch(err => {
    console.log(err);
  });
};

exports.deleteItem= (req, res, next) => {
};
