DELETE FROM categories;
DELETE FROM cities;
DELETE FROM states;


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

