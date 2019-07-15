create table bookings (
	id              INT AUTO_INCREMENT PRIMARY KEY,
    itemId          INT NOT NULL,
    borrowerId      VARCHAR(255) NOT NULL,
	status          VARCHAR(255) NOT NULL,
	startDate       DATETIME,
    returnDate      DATETIME,
    constraint      FK_BOOKING_ITEM foreign key (itemId) references items (id)
);
-- user6 to user10 booking 50 items from user1 to user5
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (1, 1, 'user6@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (2, 2, 'user7@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (3, 3, 'user7@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (4, 4, 'user8@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (5, 5, 'user7@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (6, 6, 'user6@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (7, 7, 'user8@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (8, 8, 'user9@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (9, 9, 'user9@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (10, 10, 'user7@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (11, 11, 'user10@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (12, 12, 'user7@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (13, 13, 'user10@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (14, 14, 'user10@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (15, 15, 'user6@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (16, 16, 'user7@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (17, 17, 'user9@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (18, 18, 'user6@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (19, 19, 'user6@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (20, 20, 'user6@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (21, 21, 'user10@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (22, 22, 'user10@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (23, 23, 'user7@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (24, 24, 'user8@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (25, 25, 'user9@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (26, 26, 'user7@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (27, 27, 'user6@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (28, 28, 'user7@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (29, 29, 'user7@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (30, 30, 'user9@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (31, 31, 'user9@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (32, 32, 'user10@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (33, 33, 'user9@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (34, 34, 'user7@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (35, 35, 'user9@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (36, 36, 'user8@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (37, 37, 'user8@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (38, 38, 'user7@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (39, 39, 'user7@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (40, 40, 'user9@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (41, 41, 'user7@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (42, 42, 'user6@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (43, 43, 'user8@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (44, 44, 'user10@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (45, 45, 'user9@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (46, 46, 'user9@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (47, 47, 'user8@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (48, 48, 'user9@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (49, 49, 'user7@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (50, 50, 'user6@shareandget.com', 'Pending', '2019-08-01 00:00:00', '2019-08-03 00:00:00');