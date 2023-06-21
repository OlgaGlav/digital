--liquibase formatted sql
--changeset OlgaGlavdel:items-create-table

CREATE TABLE items
(
    item_id      bigserial PRIMARY KEY,
    position_id  bigserial NOT NULL references positions (id),
    price        numeric   NOT NULL,
    actual_count decimal,
    is_remove    boolean DEFAULT FALSE,
    CONSTRAINT items_count CHECK ( actual_count >= 0),
    CONSTRAINT items_price CHECK ( price >= 0)
);

