create table wishlists(
    itemId          INT,
    ownerId         VARCHAR(255),     
    endDate         DATETIME,
    PRIMARY KEY     (itemId, ownerId)
);