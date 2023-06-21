--liquibase formatted sql
--changeset OlgaGlavdel:positions-insert-data

INSERT INTO positions (name, description)
VALUES ('Milk', '2.6% Minskaya Marka');

INSERT INTO positions (name, description)
VALUES ('Chokolate', 'Milka');

INSERT INTO positions (name)
VALUES ('Potato');


--rollback delete from positions where name='Milk';
--rollback delete from positions where name='Chokolate';
--rollback delete from positions where name='Potato';