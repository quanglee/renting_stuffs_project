// place the users logic here
const { firebaseAdmin, firebase } = require('../util/firebase');
const User = require('../models/user');

exports.loginUser = (req, res, next) => {
    const email = req.body.email;
    const password = req.body.password;
    firebase.auth().signInWithEmailAndPassword(email, password)
        .then(result => {
            User.findUserByEmail(result.user.email)
                    .then(userDetails => {
                        // create token and send back to client
                        firebaseAdmin.auth().createCustomToken(result.user.uid)
                            .then(customToken => {
                                console.log(customToken);
                                res.status(200).json({
                                    message: 'success',
                                    token: customToken,
                                    uid: result.user.uid,
                                    displayName: result.user.displayName,
                                    lat: userDetails.data().lat,
                                    lng: userDetails.data().lng,
                                    role: userDetails.data().role
                                })
                            });
                    }).catch(err => {
                        res.status(401).json({
                            message: "Unable to login"
                        });
                    });
        })
        .catch(error => {
            const errorMessage = error.message;
            res.status(401).json({
                message: errorMessage
            });
        })
}

exports.registerUser = (req, res, next) => {
    console.log("register user");
    const email = req.body.email;
    const password = req.body.password;
    const displayName = req.body.displayName;
    firebaseAdmin.auth().createUser({
        email: email,
        password: password,
        displayName: displayName
    }).then(userRecord => {
        // TODO: save user into firebase db
        User.saveUser({
            email: email,
            username: displayName,
            role: false,
            lat: "",
            lng: "",
            isActive: true
        }).then(resultData => {
            res.status(201).json({
                email: userRecord.email,
                displayName: userRecord.displayName,
                uid: userRecord.uid
            });
        }).catch(err => {
            res.status(400).json({
                message: "unable to create record"
            });
        });
    }).catch(err => {
        res.status(400).json({
            message: "unable to create record"
        });
    });
}