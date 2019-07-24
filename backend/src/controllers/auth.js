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
                      firebase.auth().currentUser.getIdToken(/* forceRefresh */ true)
                        .then(idToken => {
                          console.log(idToken);
                          res.status(200).json({
                            message: 'success',
                            access_token: idToken,
                            refresh_token: result.user.refreshToken,
                            user: {
                              uid: result.user.uid,
                              email: result.user.email,
                              username: result.user.displayName,
                              isAdmin: userDetails.data().role,
                              lat: userDetails.data().lat,
                              lng: userDetails.data().lng,
                              isActive: userDetails.data().isActive
                            }
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
    const lat = req.body.lat;
    const lng = req.body.lng;
    firebaseAdmin.auth().createUser({
        email: email,
        password: password,
        displayName: displayName
    }).then(userRecord => {
        // TODO: save user into firebase db
        User.saveUser({
            email: email,
            username: displayName,
            isAdmin: false,
            lat: lat,
            lng: lng,
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

exports.refreshToken = (req, res, next) => {
  if (!req.body.refreshToken) {
    return res.status(400).send({
      success: 'false',
      message: 'refreshToken is required',
    });
  }
  const refreshToken = req.body.refreshToken;
  const {FIREBASE_API_KEY} = require('../config');
  const XMLHttpRequest = require("xmlhttprequest").XMLHttpRequest;

  let xhr = new XMLHttpRequest();

  xhr.open('POST', 'https://securetoken.googleapis.com/v1/token?key=' + FIREBASE_API_KEY, false);
  xhr.send(JSON.stringify({
    refresh_token: refreshToken,
    grant_type: 'refresh_token'
  }));

  res.status(xhr.status).json(JSON.parse(xhr.responseText));
};
