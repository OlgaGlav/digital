--liquibase formatted sql
--changeset OlgaGlavdel:orders-create-table

CREATE TABLE orders
(
    order_id    bigserial PRIMARY KEY,
    user_id     bigserial NOT NULL references users (id),
    summa       numeric   NOT NULL,
    date_create date    DEFAULT now(),
    is_remove   boolean DEFAULT FALSE
);

