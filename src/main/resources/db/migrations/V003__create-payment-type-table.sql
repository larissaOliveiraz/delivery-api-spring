CREATE TABLE payment_types
(
    id          SERIAL UNIQUE      NOT NULL,
    description VARCHAR(60) UNIQUE NOT NULL,

    PRIMARY KEY (id)
);