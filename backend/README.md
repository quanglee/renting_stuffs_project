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

# create account service json file on Firebase
* Firebase Console -> Project settings -> Service Account 
* Select node js -> generate new private key
* update .env file to load the file.