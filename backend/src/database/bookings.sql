create table bookings (
	id              INT AUTO_INCREMENT PRIMARY KEY,
    itemId          INT NOT NULL,
    borrowerId      VARCHAR(255) NOT NULL,
	status          VARCHAR(255) NOT NULL,
	startDate       DATETIME,
    returnDate      DATETIME,
    constraint      FK_BOOKING_ITEM foreign key (itemId) references items (id)
);