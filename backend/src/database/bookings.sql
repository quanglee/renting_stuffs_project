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
