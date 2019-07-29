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
  console.log("Create item");
  const lat = req.body.lat == null ? req.user.lat : req.body.lat;
  const lng = req.body.lng == null ? req.user.lng : req.body.lng;

  itemImageURL = UPLOAD_IMAGE_FOLDER + req.file.filename;
  const item = {
      ownerId: req.user.email,
      name: req.body.name,
      description: req.body.description,
      condition: req.body.condition,
      category: req.body.category,
      imageURLs: itemImageURL,
      tags: req.body.tags,
      lat: lat,
      lng: lng,
      price: req.body.price
  };
  Item.addItem(item).then(([rows, fields]) => {
      res.status(201).json(item)
  }).catch(err => {
      console.log(err);
  });
};

exports.editItem= (req, res, next) => {
};

exports.deleteItem= (req, res, next) => {
};
