var admin = require('firebase-admin');

const isAuthenticated = (req, res, next) => {
  const token = req.get("Authorization");
  admin.auth().verifyIdToken(token)
  .then(decodedToken => {
    let uid = decodedToken.uid;
    req.loggedInEmail = decodedToken.email;
    next();
  }).catch(function(error) {
    console.log(error);
    res.status(401).json({
      message: "Invalid token"
    });
  });
};

module.exports = isAuthenticated;
