CREATE TABLE permissions
(
    id          SERIAL UNIQUE NOT NULL,
    name        VARCHAR(50)   NOT NULL,
    description VARCHAR(255)  NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE roles
(
    id   SERIAL UNIQUE NOT NULL,
    name VARCHAR(50)   NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE roles_permissions
(
    role_id       BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,

    PRIMARY KEY (role_id, permission_id)
);

CREATE TABLE users
(
    id         SERIAL UNIQUE       NOT NULL,
    name       VARCHAR(50)         NOT NULL,
    email      VARCHAR(100) UNIQUE NOT NULL,
    password   VARCHAR(100)        NOT NULL,
    created_at TIMESTAMP           NOT NULL DEFAULT NOW(),

    PRIMARY KEY (id)
);

CREATE TABLE users_roles
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,

    PRIMARY KEY (user_id, role_id)
);

ALTER TABLE roles_permissions
    ADD CONSTRAINT fk_roles_permissions_role_id FOREIGN KEY (role_id) REFERENCES roles (id);

ALTER TABLE roles_permissions
    ADD CONSTRAINT fk_roles_permissions_permission_id FOREIGN KEY (permission_id) REFERENCES permissions (id);

ALTER TABLE users_roles
    ADD CONSTRAINT fk_users_roles_user_id FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE users_roles
    ADD CONSTRAINT fk_users_roles_role_id FOREIGN KEY (role_id) REFERENCES roles (id);