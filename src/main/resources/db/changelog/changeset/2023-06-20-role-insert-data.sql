--liquibase formatted sql
--changeset OlgaGlavdel:roles-insert-data

INSERT INTO roles (name)
VALUES ('ROLE_ADMIN');

INSERT INTO roles (name)
VALUES ('ROLE_USER');

--rollback delete from roles where name='ADMIN';
--rollback delete from roles where name='USER';