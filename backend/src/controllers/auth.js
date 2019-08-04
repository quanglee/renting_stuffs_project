// place the users logic here
const { firebase, firebaseAdmin } = require('../util/firebase');
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
                              isAdmin: userDetails.data().isAdmin,
                              lat: userDetails.data().lat,
                              lng: userDetails.data().lng,
                              isActive: userDetails.data().isActive,
                              address: userDetails.data().address
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

exports.testFCMMessage = (req, res, next) => {
  
  //Subribe topic 
  // firebaseAdmin.messaging().subscribeToTopic("etCFh9ria70:APA91bG25YIYldIrrtm4yVXhEjgNdKrgTGrA4bAP4PMSZV_OmeOP1skb5lYO9Ukw8UfAE-XIai7DJsCfxWlbRqWjXjQcksF4PKhx6tiI0V0TnVwK9VXMHBI9smAfWM08PmWo-DKzHtAS", "item1")
  //   .then(response => {
  //     console.log('Successfully subscribed to topic:', response);
  //   })

  var message = {
    notification: {
      "title": "FCM Message",
      "body": "This is a message from FCM"
    },
    topic: "SHAREANDGET_ITEM_10"
  };
  
  firebaseAdmin.messaging().send(message)
    .then(response => {
      console.log('Successfully sent message:', response);
    })

  res.status(200).json({
    message: "Hello FMC"
  });
};
