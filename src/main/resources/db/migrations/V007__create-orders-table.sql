CREATE TABLE orders
(
    id                   SERIAL UNIQUE      NOT NULL,
    code                 VARCHAR(36) UNIQUE NOT NULL DEFAULT GEN_RANDOM_UUID(),
    subtotal             DECIMAL(10, 2)     NOT NULL,
    shipment             DECIMAL(10, 2)     NOT NULL,
    total                DECIMAL(10, 2)     NOT NULL,

    restaurant_id        BIGINT             NOT NULL,
    payment_type_id      BIGINT             NOT NULL,
    client_id            BIGINT             NOT NULL,

    address_city_id      BIGINT             NOT NULL,
    address_zip_code     VARCHAR(9)         NOT NULL,
    address_public_area  VARCHAR(100)       NOT NULL,
    address_number       VARCHAR(20)        NOT NULL,
    address_neighborhood VARCHAR(60)        NOT NULL,

    status               VARCHAR(10)        NOT NULL,
    creation_date        TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP,
    confirmation_date    TIMESTAMP,
    delivery_date        TIMESTAMP,
    cancellation_date    TIMESTAMP,

    PRIMARY KEY (id),

    CONSTRAINT fk_orders_restaurant_id FOREIGN KEY (restaurant_id) REFERENCES restaurants (id),
    CONSTRAINT fk_orders_payment_type_id FOREIGN KEY (payment_type_id) REFERENCES payment_types (id),
    CONSTRAINT fk_orders_client_id FOREIGN KEY (client_id) REFERENCES users (id),
    CONSTRAINT fk_orders_city_id FOREIGN KEY (address_city_id) REFERENCES cities (id)
);

CREATE TABLE order_items
(
    id          SERIAL UNIQUE  NOT NULL,
    quantity    SMALLINT       NOT NULL,
    unit_price  DECIMAL(10, 2) NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,

    product_id  BIGINT         NOT NULL,
    order_id    BIGINT         NOT NULL,

    PRIMARY KEY (id),

    CONSTRAINT uk_order_item_product UNIQUE (order_id, product_id),
    CONSTRAINT fk_order_items_product_id FOREIGN KEY (product_id) REFERENCES products (id),
    CONSTRAINT fk_order_items_order_id FOREIGN KEY (order_id) REFERENCES orders (id)
)