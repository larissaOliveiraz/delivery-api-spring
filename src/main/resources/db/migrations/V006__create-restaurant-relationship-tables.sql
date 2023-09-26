CREATE TABLE restaurants_payment_types
(
    restaurant_id   BIGINT NOT NULL,
    payment_type_id BIGINT NOT NULL,

    PRIMARY KEY (restaurant_id, payment_type_id)
);

CREATE TABLE restaurants_users_responsible
(
    restaurant_id BIGINT NOT NULL,
    user_id       BIGINT NOT NULL,

    PRIMARY KEY (restaurant_id, user_id)
);

ALTER TABLE restaurants_payment_types
    ADD CONSTRAINT fk_restaurants_payment_types_restaurant_id FOREIGN KEY (restaurant_id) REFERENCES restaurants (id);

ALTER TABLE restaurants_payment_types
    ADD CONSTRAINT fk_restaurants_payment_types_payment_type_id FOREIGN KEY (payment_type_id) REFERENCES payment_types (id);

ALTER TABLE restaurants_users_responsible
    ADD CONSTRAINT fk_restaurants_users_restaurant_id FOREIGN KEY (restaurant_id) REFERENCES restaurants (id);

ALTER TABLE restaurants_users_responsible
    ADD CONSTRAINT fk_restaurants_users_user_id FOREIGN KEY (user_id) REFERENCES users (id);

