--liquibase formatted sql
--changeset OlgaGlavdel:positions-create-table

CREATE TABLE positions
(
    id          bigserial PRIMARY KEY,
    name        varchar NOT NULL,
    description varchar(255),
    is_remove   boolean DEFAULT FALSE
);

