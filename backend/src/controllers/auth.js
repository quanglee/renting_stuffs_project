// place the users logic here
const firebaseAdmin = require('../util/firebase');

exports.loginUser = (req, res, next) => {
    console.log("login user");
    firebaseAdmin.auth().getUserByEmail("admin@shareandget.com")
    .then(userRecord =>  {
      // See the UserRecord reference doc for the contents of userRecord.
      console.log('Successfully fetched user data:', userRecord.toJSON());
    })
    .catch(error => {
      console.log('Error fetching user data:', error);
    });

    res.status(200).json({
        message: 'success',
        token: 'token abac'
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
        res.status(201).json({
            email: userRecord.email,
            displayName: userRecord.displayName,
            uid: userRecord.uid
        })
    }).catch(err => {
        console.log(err);
    });
}