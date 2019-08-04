create table bookings (
	id              INT AUTO_INCREMENT PRIMARY KEY,
    itemId          INT NOT NULL,
    borrowerId      VARCHAR(255) NOT NULL,
	status          VARCHAR(255) NOT NULL,
	startDate       DATETIME,
    returnDate      DATETIME,
    constraint      FK_BOOKING_ITEM foreign key (itemId) references items (id)
);
-- user2 booking 3 items from user1
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (1, 1, 'user2@shareandget.com', 'Pending', '2019-08-10 08:00:00', '2019-08-13 20:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (2, 2, 'user2@shareandget.com', 'Pending', '2019-08-10 08:00:00', '2019-08-13 20:00:00');
insert into bookings (id, itemId, borrowerId, status, startDate, returnDate) values (3, 3, 'user2@shareandget.com', 'Pending', '2019-08-10 08:00:00', '2019-08-13 20:00:00');
