const { firebase } = require("./firebase");
const isAuthenticated = (req, res, next) => {
    const token = req.get("Authorization");
    firebase.auth().signInWithCustomToken(token)
    .then(decodedToken => {
        let uid = decodedToken.user.uid;
        req.loggedInEmail = decodedToken.user.email;
        next();
    }).catch(function(error) {
        res.status(401).json({
            message: "Invalid token"
        });
    });
};

module.exports = isAuthenticated;