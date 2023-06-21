--liquibase formatted sql
--changeset OlgaGlavdel:users-insert-data

INSERT INTO users (username, email, date_birth, role_id)
VALUES ('OlgaAdmin11', 'pin007@tut.by', '2009-09-09', 1);

INSERT INTO users (username, email, date_birth, role_id)
VALUES ('username11', 'olga.glavdel@gmail.com', '1999-01-11', 2);

--rollback delete from users where username='OlgaAdmin11';
--rollback delete from users where username='username11';