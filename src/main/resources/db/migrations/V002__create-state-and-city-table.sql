CREATE TABLE states
(
    id   SERIAL UNIQUE NOT NULL,
    name VARCHAR(100)  NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE cities
(
    id       SERIAL UNIQUE NOT NULL,
    name     VARCHAR(100)  NOT NULL,
    state_id BIGINT        NOT NULL,

    PRIMARY KEY (id)
);

ALTER TABLE cities
    ADD CONSTRAINT fk_state_id FOREIGN KEY (state_id) REFERENCES states (id);