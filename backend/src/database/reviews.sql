create table reviews (
	id              INT AUTO_INCREMENT PRIMARY KEY,
    ownerId         VARCHAR(255),
    itemId          INT NOT NULL,
    borrowerId      VARCHAR(255) NOT NULL,
	title           VARCHAR(255),
	content         TEXT,
    rating          DECIMAL NOT NULL DEFAULT 0,
    constraint      FK_REVIEW_ITEM foreign key (itemId) references items (id)
);