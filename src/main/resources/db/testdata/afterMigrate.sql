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
FROM roles;


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

INSERT INTO roles_permissions (role_id, permission_id)
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (3, 1),
       (3, 2),
       (3, 3)