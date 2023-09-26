CREATE TABLE categories
(
    id   SERIAL UNIQUE NOT NULL,
    name VARCHAR(60)   NOT NULL,

    PRIMARY KEY (id)
);