--liquibase formatted sql
--changeset OlgaGlavdel:role-create-table

CREATE TABLE roles
(
    id   serial PRIMARY KEY,
    name varchar NOT NULL UNIQUE
);