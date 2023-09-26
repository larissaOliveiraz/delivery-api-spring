DELETE
FROM products;
DELETE
FROM restaurants_users_responsible;
DELETE
FROM restaurants_payment_types;
DELETE
FROM restaurants;
DELETE
FROM categories;
DELETE
FROM cities;
DELETE
FROM states;
DELETE
FROM payment_types;
DELETE
FROM roles_permissions;
DELETE
FROM permissions;
DELETE
FROM users_roles;
DELETE
FROM roles;
DELETE
FROM users;

INSERT INTO categories (id, name)
VALUES (1, 'Brazilian'),
       (2, 'Italian'),
       (3, 'French'),
       (4, 'Mexican'),
       (5, 'Japanese');
ALTER SEQUENCE categories_id_seq RESTART WITH 6;

INSERT INTO states (id, name)
VALUES (1, 'São Paulo'),
       (2, 'Rio de Janeiro'),
       (3, 'Bahia'),
       (4, 'Minas Gerais');
ALTER SEQUENCE states_id_seq RESTART WITH 5;

INSERT INTO cities (id, name, state_id)
VALUES (1, 'Campinas', 1),
       (2, 'Paraty', 2),
       (3, 'Salvador', 3),
       (4, 'Belo Horizonte', 4);
ALTER SEQUENCE cities_id_seq RESTART WITH 5;

INSERT INTO payment_types (id, description)
VALUES (1, 'Credit Card'),
       (2, 'Debit Card'),
       (3, 'Money'),
       (4, 'Transfer');
ALTER SEQUENCE payment_types_id_seq RESTART WITH 5;

INSERT INTO permissions (id, name, description)
VALUES (1, 'READ_CATEGORIES', 'Permission to read categories.'),
       (2, 'EDIT_CATEGORIES', 'Permission to edit categories.'),
       (3, 'EDIT_RESTAURANT', 'Permission to edit restaurant.');
ALTER SEQUENCE permissions_id_seq RESTART WITH 4;

INSERT INTO roles (id, name)
VALUES (1, 'SECRETARY'),
       (2, 'CLIENT'),
       (3, 'OWNER');
ALTER SEQUENCE roles_id_seq RESTART WITH 4;

INSERT INTO users (id, name, email, password, created_at)
VALUES (1, 'Larissa', 'larissa@gmail.com', '123', CURRENT_TIMESTAMP),
       (2, 'Jurema', 'jurema@gmail.com', '321', CURRENT_TIMESTAMP),
       (3, 'Pantufa', 'pantufa@gmail.com', 'pantufa', CURRENT_TIMESTAMP);
ALTER SEQUENCE users_id_seq RESTART WITH 4;

INSERT INTO roles_permissions (role_id, permission_id)
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (3, 1),
       (3, 2),
       (3, 3);

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 3),
       (2, 2),
       (2, 1),
       (3, 1);

INSERT INTO restaurants (id, name, category_id, shipment, active, open, created_at, updated_at)
VALUES (1, 'PizzaITA', 2, 9.9, true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (2, 'ChurrasBRA', 1, 5.9, true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (3, 'TacosMEX', 4, 2.9, true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (4, 'SushiJAP', 5, 12.9, true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (5, 'CroissantFRA', 3, 10.9, true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
ALTER SEQUENCE restaurants_id_seq RESTART WITH 6;

INSERT INTO products (id, name, description, price, active, restaurant_id)
VALUES (1, 'Mozzarella', 'Mozzarella Pizza', 25.5, true, 1),
       (2, 'Chicken stick', 'Chicken on a stick.', 15.9, true, 2),
       (3, 'Meat', 'Meat Taco', 30, true, 3),
       (4, 'Sushi combo', 'Variety of sushi', 105.5, true, 4),
       (5, 'Chocolate', 'Chocolate Croissant', 12.5, true, 5);
ALTER SEQUENCE products_id_seq RESTART WITH 6;

INSERT INTO restaurants_payment_types (restaurant_id, payment_type_id)
VALUES (1, 1),
       (2, 1),
       (2, 2),
       (3, 1),
       (3, 3),
       (4, 1),
       (4, 4),
       (5, 1);

INSERT INTO restaurants_users_responsible (restaurant_id, user_id)
VALUES (1, 1),
       (2, 1),
       (2, 2),
       (3, 2),
       (3, 3),
       (4, 3),
       (4, 1),
       (5, 3);