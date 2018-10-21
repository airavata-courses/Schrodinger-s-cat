-- CREATE DATABASE IF NOT EXISTS loginAuth;
-- CREATE USER IF NOT EXISTS 'nawaz'@'localhost' IDENTIFIED WITH mysql_native_password BY 'password';
-- GRANT ALL PRIVILEGES ON loginAuth.* TO 'nawaz'@'localhost';

-- sudo -u postgres psql -c 'create database loginAuth;'
-- sudo -u postgres psql -c 'CREATE USER nawaz WITH PASSWORD 'password';'
-- sudo -u postgres psql -c 'GRANT ALL PRIVILEGES ON DATABASE loginAuth TO nawaz;'
--
-- createdb loginAuth
-- createuser -s postgres;
-- sudo -u nawaz psql -c 'grant all privileges on database loginAuth to nawaz;'
-- sudo -u loginAuth psql -c "create role NewRole with login password 'secret';"

createuser -s postgres;
