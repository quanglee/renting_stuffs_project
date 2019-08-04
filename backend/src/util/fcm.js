const { firebaseAdmin } = require('./firebase');
const TOPIC_ITEM_PREFIX = "SHAREANDGET_ITEM_";
module.exports = class FCM {
  
  constructor() {
  }

  static subscribeFromTopic(registrationTokens, topic) {
    const topicName = TOPIC_ITEM_PREFIX + topic;
    firebaseAdmin.messaging().subscribeToTopic(registrationTokens, topicName)
      .then(response => {
        console.log('Successfully subscribed to topic:', response);
      })
    
  }

  static sendNotificationToSubcribeTopic(topicItem, itemName) {
    console.log("SENDING NOTIFICATION");
    const topicName = TOPIC_ITEM_PREFIX + topicItem;
    const bodyMessage = `item \"${itemName}\" is ready to book now`;
    var message = {
        notification: {
          "title": "Wishlist Notification",
          "body": bodyMessage
        },
        topic: topicName
      };
    firebaseAdmin.messaging().send(message)
    .then(response => {
        console.log('Successfully sent message:', response);
    });
  }

  static unSubscribeFromTopic(registrationTokens, topic) {
    const topicName = TOPIC_ITEM_PREFIX + topic;
    firebaseAdmin.messaging().unsubscribeFromTopic(registrationTokens, topicName)
      .then(response => {
        console.log('Successfully unsubscribed from topic:', response);
      }).catch(error => {
        console.log('Error unsubscribing from topic:', error);
      })
  }
}