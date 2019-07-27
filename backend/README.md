# renting_stuffs_project - BACKEND
Leave your backend stuff in this folder

# download all dependencies
npm install

# create .env file
<!-- Custom as needed -->
NODE_PORT=3000
NODE_HOST='0.0.0.0'
DB_HOST='localhost'
DB_USER='root'
DB_PASS='root'
DB_DATABASE='renting_db'
DB_CONNECT_LIMIT=10
FIREBASE_SERVICE_ACCOUNT_JSON="../../service-account-file.json"
FIREBASE_DATABASE_URL="https://shareandgetapps.firebaseio.com"
FIREBASE_API_KEY="<YOUR KEY>"
FIREBASE_AUTH_DOMAIN="shareandgetapps.firebaseapp.com"
FIRE_BASE_PROJECT_ID="shareandgetapps"
FIREBASE_STORAGEBUCKET="shareandgetapps.appspot.com"
FIREBASE_MESSAGING_SENDER_ID="<YOUR NUMBER>"
FIREBASE_APP_ID="<YOUR NUMBER>"
UPLOAD_IMAGE_FOLDER="/images/"

# create account service json file on Firebase
* Firebase Console -> Project settings -> Service Account 
* Select node js -> generate new private key
* update .env file to load the file.

# for upload image
* create images folder inside backend/src/
