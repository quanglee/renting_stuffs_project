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
-- 10 items of user1 to user10 (user1's items from 1-10; ... ; user10's items from 91-100) 
insert into items (id, ownerId, name, description, `condition`, category, imageURLs, tags, numberOfReview, averageRating, lat, lng, price, pickupAddress, isActive) values (1, 'user1@shareandget.com', 'mauris eget massa tempor convallis nulla neque libero convallis', 'quisque ut erat curabitur gravida nisi at nibh in hac', 'Good', 'Others', 'https://picsum.photos/id/172/320/320', 'at turpis donec posuere metus vitae ipsum', 0, 0, 49.203543499999995, -122.91276149999997, 12, '700 Royal Ave', 1);
insert into items (id, ownerId, name, description, `condition`, category, imageURLs, tags, numberOfReview, averageRating, lat, lng, price, pickupAddress, isActive) values (2, 'user1@shareandget.com', 'natoque penatibus', 'maecenas ut massa quis augue luctus tincidunt nulla mollis molestie', 'Good', 'Cars', 'https://picsum.photos/id/286/320/320', 'integer tincidunt', 0, 0, 49.203543499999995, -122.91276149999997, 21, '700 Royal Ave', 1);
insert into items (id, ownerId, name, description, `condition`, category, imageURLs, tags, numberOfReview, averageRating, lat, lng, price, pickupAddress, isActive) values (3, 'user1@shareandget.com', 'convallis nulla', 'tristique est et tempus semper est quam pharetra magna ac consequat metus', 'Good', 'Electronics', 'https://picsum.photos/id/855/320/320', 'natoque penatibus et magnis dis parturient montes nascetur', 0, 0, 49.203543499999995, -122.91276149999997, 167, '700 Royal Ave', 0);
insert into items (id, ownerId, name, description, `condition`, category, imageURLs, tags, numberOfReview, averageRating, lat, lng, price, pickupAddress, isActive) values (4, 'user1@shareandget.com', 'aliquam convallis nunc proin at turpis', 'vehicula condimentum curabitur in libero ut massa volutpat convallis morbi odio odio elementum eu', 'New', 'Others', 'https://picsum.photos/id/836/320/320', 'ipsum primis in faucibus orci luctus et ultrices posuere cubilia', 0, 0, 49.203543499999995, -122.91276149999997, 36, '700 Royal Ave', 1);
insert into items (id, ownerId, name, description, `condition`, category, imageURLs, tags, numberOfReview, averageRating, lat, lng, price, pickupAddress, isActive) values (5, 'user1@shareandget.com', 'blandit ultrices enim lorem', 'rutrum at lorem integer tincidunt ante vel ipsum praesent blandit', 'Used', 'Others', 'https://picsum.photos/id/819/320/320', 'porttitor pede justo eu massa donec dapibus', 0, 0, 49.203543499999995, -122.91276149999997, 71, '700 Royal Ave', 1);
insert into items (id, ownerId, name, description, `condition`, category, imageURLs, tags, numberOfReview, averageRating, lat, lng, price, pickupAddress, isActive) values (6, 'user1@shareandget.com', 'consequat dui nec nisi', 'faucibus orci luctus et ultrices posuere cubilia curae donec pharetra magna vestibulum', 'New', 'Home and Garden', 'https://picsum.photos/id/698/320/320', 'penatibus et magnis dis parturient montes', 0, 0, 49.203543499999995, -122.91276149999997, 122, '700 Royal Ave', 1);
insert into items (id, ownerId, name, description, `condition`, category, imageURLs, tags, numberOfReview, averageRating, lat, lng, price, pickupAddress, isActive) values (7, 'user1@shareandget.com', 'odio condimentum id luctus nec molestie', 'sem mauris laoreet ut rhoncus aliquet pulvinar sed nisl nunc rhoncus dui vel', 'Like new', 'Electronics', 'https://picsum.photos/id/660/320/320', 'ipsum primis in faucibus', 0, 0, 49.203543499999995, -122.91276149999997, 68, '700 Royal Ave', 1);
insert into items (id, ownerId, name, description, `condition`, category, imageURLs, tags, numberOfReview, averageRating, lat, lng, price, pickupAddress, isActive) values (8, 'user1@shareandget.com', 'viverra dapibus nulla suscipit ligula', 'pulvinar lobortis est phasellus sit amet erat nulla tempus vivamus in felis eu sapien cursus vestibulum proin eu', 'New', 'Electronics', 'https://picsum.photos/id/177/320/320', 'mi sit amet lobortis', 0, 0, 49.203543499999995, -122.91276149999997, 65, '700 Royal Ave', 0);
insert into items (id, ownerId, name, description, `condition`, category, imageURLs, tags, numberOfReview, averageRating, lat, lng, price, pickupAddress, isActive) values (9, 'user1@shareandget.com', 'vitae quam suspendisse potenti nullam porttitor lacus at turpis donec', 'consequat in consequat ut nulla sed accumsan felis ut at dolor quis odio consequat varius integer ac leo pellentesque ultrices', 'Like new', 'Home and Garden', 'https://picsum.photos/id/692/320/320', 'mattis nibh ligula', 0, 0, 49.203543499999995, -122.91276149999997, 103, '700 Royal Ave', 1);
insert into items (id, ownerId, name, description, `condition`, category, imageURLs, tags, numberOfReview, averageRating, lat, lng, price, pickupAddress, isActive) values (10, 'user1@shareandget.com', 'eget rutrum at lorem integer tincidunt ante vel', 'hac habitasse platea dictumst etiam faucibus cursus urna ut tellus nulla ut', 'Like new', 'Others', 'https://picsum.photos/id/994/320/320', 'sodales sed tincidunt eu felis fusce posuere', 0, 0, 49.203543499999995, -122.91276149999997, 135, '700 Royal Ave', 0);
