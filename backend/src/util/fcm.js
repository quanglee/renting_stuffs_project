const { firebaseAdmin } = require('./firebase');

const TOPIC_ITEM_PREFIX = "SHAREANDGET_ITEM_";

const sendNotificationToSubcribeTopic = (topicItem) => {
    console.log("SENDING NOTIFICATION");
    const topicName = TOPIC_ITEM_PREFIX + topicItem;
    var message = {
        notification: {
          "title": "Booking Update Notification",
          "body": "The item in your wishlist is ready to book now :)"
        },
        topic: topicName
      };
      
    firebaseAdmin.messaging().send(message)
    .then(response => {
        console.log('Successfully sent message:', response);
    });
}    
//     if (req.get("Authorization") == null) {
//       res.status(401).json({
//         message: "You need to provide the jwt token in the Authorization field in the header"
//       });
//       return;
//     }
//     const token = req.get("Authorization");
//     admin.auth().verifyIdToken(token)
//     .then(user => {
//       User.findUserByEmail(user.email)
//         .then(doc => {
//           Object.assign(user, doc.data());
//           req.user = user; 
//           console.log(user);
//           next();
//         }).catch(err => {
//           console.log(err);
//           res.status(401).json({
//             message: "Invalid token"
//           });
//         });
//     }).catch(function(error) {
//       console.log(error);
//       res.status(401).json({
//         message: "Invalid token"
//       });
//     });
//   };
  
  module.exports = {
    sendNotificationToSubcribeTopic
  };