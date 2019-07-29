var admin = require('firebase-admin');
var jwt = require('jsonwebtoken');
const User = require('../models/user');

const isAuthenticated = (req, res, next) => {
  if (req.get("Authorization") == null) {
    res.status(401).json({
      message: "You need to provide the jwt token in the Authorization field in the header"
    });
    return;
  }
  const token = req.get("Authorization");
  const pkey = "-----BEGIN CERTIFICATE-----\nMIIDHDCCAgSgAwIBAgIID1iHQiUBN2IwDQYJKoZIhvcNAQEFBQAwMTEvMC0GA1UE\nAxMmc2VjdXJldG9rZW4uc3lzdGVtLmdzZXJ2aWNlYWNjb3VudC5jb20wHhcNMTkw\nNzE1MjEyMDU2WhcNMTkwODAxMDkzNTU2WjAxMS8wLQYDVQQDEyZzZWN1cmV0b2tl\nbi5zeXN0ZW0uZ3NlcnZpY2VhY2NvdW50LmNvbTCCASIwDQYJKoZIhvcNAQEBBQAD\nggEPADCCAQoCggEBAM4iPM/AlxhVbKeXW8aoJt9/4zFOHbHk5ceLsMY9muK/tgoS\n6won3KXpg2PUJJTlLZHBr1T1AdRDIQRKkcuuhu7xxSvz6o8vHuE3jKNVCFd48ien\nvJXP2Z624xcwT+9494TvFXGJLMpNK4IHooQyHWxGDxN1K2WawejnC/XoYxt+GsBQ\nn1LrHi524hb44jHOtMoGcyxahMx6qDQhMHPHdGtP1D57rz+GEXIPyP288Hs/2tUb\nE+Jk3WT8tHeSWohu0lOiFRa+byCxfi1OW5bvc9Fh4o8SUeAGpQCqtPwPP+GZGtpE\npmY3lYXkVzuXZ65N9w1wJrcSWgPXqdAwSR5mYSsCAwEAAaM4MDYwDAYDVR0TAQH/\nBAIwADAOBgNVHQ8BAf8EBAMCB4AwFgYDVR0lAQH/BAwwCgYIKwYBBQUHAwIwDQYJ\nKoZIhvcNAQEFBQADggEBALu6v/apoeiqkWjCNgeO74zNZllMEaFlUVW0jeRvHwF0\nER+G2tKyVB0VSkKwQNuPdIuhRBnP1gdGm/Ph/379ZGJKD+ZOU8CL2ze5pC5FsOnA\nhq0hUw/9w1guVefI2+kZlTFFW6EU2K/TYsZKrREHe30KPVrDSBtcIeNlXXYzU+9E\nB+v2dBZCqEWntSue+YNfylyNCkcBzrFQZKHOcdzdpfX2H/sNbEifLKZhY5PmZsBC\n/j7taiGW67InyTjvyb/SvKTBP0jk2QdlFMABJN8IUvF8OfA5YogYI3bSgUomJgVy\nUt+82gPwVEbqRqzyTOiSDsoBwlQaGni17lpq6we+AJY=\n-----END CERTIFICATE-----\n";
  // try {
  //   let user = jwt.verify(token, pkey, {ignoreExpiration: true});
  //   User.findUserByEmail(user.email)
  //     .then(doc => {
  //       Object.assign(user, doc.data());
  //       req.user = user; 
  //       console.log(user);
  //       next();
  //     }).catch(err => {
  //       console.log(err);
  //       res.status(401).json({
  //         message: "Invalid token"
  //       });
  //     });
  // } catch (err) {
  //   res.status(401).json({
  //     message: "Invalid token"
  //   });
  // }
  admin.auth().verifyIdToken(token)
  .then(decodedToken => {
    console.log(decodedToken);
    let uid = decodedToken.uid;
    req.user = {"email" : decodedToken.email};
    console.log("util/auth.js:");
    console.log(decodedToken.firebase);
    req.loggedInEmail = decodedToken.email;
    //req.user.email = decodedToken.email;
    next();
  }).catch(function(error) {
    console.log(error);
  });
};

module.exports = isAuthenticated;
