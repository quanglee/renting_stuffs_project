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
    averageRating   DECIMAL DEFAULT 0.0,
    lat             DECIMAL,
    lng             DECIMAL,
    price           DECIMAL,
	isActive        TINYINT DEFAULT 1
);