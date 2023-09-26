CREATE TABLE restaurants
(
    id                   SERIAL UNIQUE                       NOT NULL,
    name                 VARCHAR(255)                        NOT NULL,
    category_id          BIGINT                              NOT NULL,
    shipment             DECIMAL(10, 2)                      NOT NULL,
    active               BOOLEAN   DEFAULT TRUE              NOT NULL,
    open                 BOOLEAN   DEFAULT TRUE              NOT NULL,
    created_at           TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at           TIMESTAMP                           NOT NULL,

    address_city_id      BIGINT,
    address_zip_code     VARCHAR(9),
    address_public_area  VARCHAR(100),
    address_number       VARCHAR(20),
    address_neighborhood VARCHAR(60),

    PRIMARY KEY (id)
);

CREATE TABLE products
(
    id            SERIAL UNIQUE        NOT NULL,
    name          VARCHAR(100)         NOT NULL,
    description   VARCHAR(255)         NOT NULL,
    price         DECIMAL              NOT NULL,
    active        BOOLEAN DEFAULT TRUE NOT NULL,
    restaurant_id BIGINT               NOT NULL,

    PRIMARY KEY (id)
);

ALTER TABLE restaurants
    ADD CONSTRAINT fk_restaurant_category_id FOREIGN KEY (category_id) REFERENCES categories (id);

ALTER TABLE restaurants
    ADD CONSTRAINT fk_restaurant_city_id FOREIGN KEY (address_city_id) REFERENCES cities (id);

ALTER TABLE products
    ADD CONSTRAINT fk_product_restaurant_id FOREIGN KEY (restaurant_id) REFERENCES restaurants (id);
