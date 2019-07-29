const multer = require('multer');
const path = require('path');
const fileStorage = multer.diskStorage({
    destination: (req, file, callback) => {
        callback(null, path.join(__dirname, 'images'));
    },
    filename: (req, file, callback) => {
        callback(null, new Date().toISOString().replace(/:/g,'-') + '-' + file.originalname)
    }
});

const fileFilter = (req, file, callback) => {
    if (
        file.mimetype === 'image/png' ||
        file.mimetype === 'image/jpg' ||
        file.mimetype === 'image/jpeg' ||
        file.mimetype == 'image/*'
    ) {
        callback(null, true);
    } else {
        callback(null, false);
    }
}

const uploadImageService = multer({storage: fileStorage, fileFilter: fileFilter});
module.exports = uploadImageService;