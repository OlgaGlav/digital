--liquibase formatted sql
--changeset OlgaGlavdel:items-insert-data

INSERT INTO items (position_id, price, actual_count)
VALUES (1, '1.76', 10.00);

INSERT INTO items (position_id, price, actual_count)
VALUES (1, '3.14', 15.00);

INSERT INTO items (position_id, price, actual_count)
VALUES (2, '2.50', 5.0);