--liquibase formatted sql
--changeset OlgaGlavdel:users-create-table

CREATE TABLE users
(
    id         bigserial PRIMARY KEY,
    username   varchar   NOT NULL UNIQUE,
    email      varchar   NOT NULL UNIQUE,
    date_birth date      NOT NULL,
    role_id    bigserial NOT NULL references roles (id)
);

