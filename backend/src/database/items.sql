-- Google Map 
-- Douglas College address 
create table items( 
  id              INT AUTO_INCREMENT PRIMARY KEY,
  ownerId         VARCHAR(255) NOT NULL,
	name            VARCHAR(255),
	description     TEXT,
  `condition`     VARCHAR(255),
  category        VARCHAR(255),
  imageURLs       TEXT,
  tags            TEXT,
  numberOfReview  INT DEFAULT 0,
  averageRating   DECIMAL(4,2) DEFAULT 0.0,
  lat             DECIMAL(10,6),
  lng             DECIMAL(10,6),
  price           DECIMAL(10,2),
  pickupAddress   VARCHAR(255),
	isActive        TINYINT DEFAULT 1
);
-- 5 items of user1 and 5 items of user2
insert into items (id, ownerId, name, description, `condition`, category, imageURLs, tags, numberOfReview, averageRating, lat, lng, price, pickupAddress, isActive) values (1, 'user1@shareandget.com', 'Large Size Trek Marlin5', 'Trek Marlin 5, Size Large, barely rode,great condition, pick up only.', 'Almost new', 'Bicycle/Bike', 'https://i.ibb.co/Pgx1Qdb/trek-marlin5.png', 'bike outdoor', 0, 0, 49.203543499999995, -122.91276149999997, 10, '700 Royal Ave', 1);
insert into items (id, ownerId, name, description, `condition`, category, imageURLs, tags, numberOfReview, averageRating, lat, lng, price, pickupAddress, isActive) values (2, 'user1@shareandget.com', 'Porsche - 911 - 2007', 'The standard 911 Carrera comes with a rear-mounted 3.6-liter, six cylinder engine. It makes 325 hp and 273 Ib-ft of torque.', 'No remarkable damage', 'Others', 'https://i.ibb.co/tqN18Kk/porsche-911-2007.jpg', 'car sport', 0, 0, 49.203543499999995, -122.91276149999997, 20, '700 Royal Ave', 1);
insert into items (id, ownerId, name, description, `condition`, category, imageURLs, tags, numberOfReview, averageRating, lat, lng, price, pickupAddress, isActive) values (3, 'user1@shareandget.com', 'Boat & Trailer', '1988 Maxum 22, Mercruiser 3.5L inline 4 cylinder engine, Needs coil and battery, Upholstery needs major repair, Trailer is in good shape, Price firm', 'No remarkable damage', 'Others', 'https://i.ibb.co/s5zC4Fw/boat-and-trailer.png', 'boat trailer outdoor', 0, 0, 49.203543499999995, -122.91276149999997, 30, '700 Royal Ave', 0);
insert into items (id, ownerId, name, description, `condition`, category, imageURLs, tags, numberOfReview, averageRating, lat, lng, price, pickupAddress, isActive) values (4, 'user1@shareandget.com', 'DJI Phantom 3 Standard Drone With Extras!', '10/10 condition, only flown 3 times. Comes with 2 batteries, 3 sets of blades, and a travel backpack ($150 value). Everything works perfectly.', 'Almost new', 'Others', 'https://i.ibb.co/9n4DJRF/dji-phantom3-drone.png', 'drone camera outdoor', 0, 0, 49.203543499999995, -122.91276149999997, 40, '700 Royal Ave', 1);
insert into items (id, ownerId, name, description, `condition`, category, imageURLs, tags, numberOfReview, averageRating, lat, lng, price, pickupAddress, isActive) values (5, 'user1@shareandget.com', 'Endeavor Paavo Snowboard', 'Endeavor Paavo Snowboard, get the hockey stick with it (left handed). Good condition. Haven’t been used in a long time. Little damage in the last picture. Original price $500.  OBO. Give me an offer and maybe you’ll get it. Running out of storage space. ', 'No remarkable damage', 'Sports/outdoor', 'https://i.ibb.co/2tT5BYH/endeavor-paavo-snowboard.jpg', 'snowboarding sport outdoor winter', 0, 0, 49.203543499999995, -122.91276149999997, 50, '700 Royal Ave', 1);
insert into items (id, ownerId, name, description, `condition`, category, imageURLs, tags, numberOfReview, averageRating, lat, lng, price, pickupAddress, isActive) values (6, 'user2@shareandget.com', 'ESCORT UV-Tex 5 Tent', 'Used few times', 'Almost new', 'Others', 'https://i.ibb.co/LzbrvYP/tent.jpg', 'picnic camping', 0, 0, 49.203543499999995, -122.91276149999997, 60, '700 Royal Ave', 1);
insert into items (id, ownerId, name, description, `condition`, category, imageURLs, tags, numberOfReview, averageRating, lat, lng, price, pickupAddress, isActive) values (7, 'user2@shareandget.com', 'Sony MDR-1000x', 'Great condition black Sony MDR-1000x over ear headphones. Class leading noise cancellation and isolation. 30 hours of battery life on a single charge', 'Almost new', 'Smartphone/electricity/camera', 'https://i.ibb.co/VvSwvZS/sony-mdr-1000x.jpg', 'headphone sony bluetooth ANC', 0, 0, 49.203543499999995, -122.91276149999997, 70, '700 Royal Ave', 1);
insert into items (id, ownerId, name, description, `condition`, category, imageURLs, tags, numberOfReview, averageRating, lat, lng, price, pickupAddress, isActive) values (8, 'user2@shareandget.com', 'Camera Canon Rebel XS', 'Works great, great starter camera. Body with 18-55mm lens', 'Almost new', 'Smartphone/electricity/camera', 'https://i.ibb.co/kJfpbjh/canon-rebel-xs.png', 'Cannon camera Rebel', 0, 0, 49.203543499999995, -122.91276149999997, 80, '700 Royal Ave', 0);
insert into items (id, ownerId, name, description, `condition`, category, imageURLs, tags, numberOfReview, averageRating, lat, lng, price, pickupAddress, isActive) values (9, 'user2@shareandget.com', 'IPhone 8 Plus - 256gb (Space Grey)', 'Comes with screen protector from XSkin, receipt is in the photos file. If you need to get a new screen protector, bring to XSkin @Metrotown and show the receipt to get tempered glass for $5. (Can keep changing it unlimited times, just show the receipt)', 'Almost new', 'Smartphone/electricity/camera', 'https://i.ibb.co/TvnSZ8S/iphone-8-plus-256.jpg', 'Apple Iphone', 0, 0, 49.203543499999995, -122.91276149999997, 90, '700 Royal Ave', 1);
insert into items (id, ownerId, name, description, `condition`, category, imageURLs, tags, numberOfReview, averageRating, lat, lng, price, pickupAddress, isActive) values (10, 'user2@shareandget.com', 'IPhone X 256GB', 'In good condition. Comes with box', 'Almost new', 'Smartphone/electricity/camera', 'https://i.ibb.co/Jzw6SkQ/iphone-x-256.jpg', 'Apple Iphone', 0, 0, 49.203543499999995, -122.91276149999997, 100, '700 Royal Ave', 0);
