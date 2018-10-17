CREATE DATABASE IF NOT EXISTS loginAuth;
CREATE USER IF NOT EXISTS 'nawaz'@'localhost' IDENTIFIED WITH mysql_native_password BY 'password';
GRANT ALL PRIVILEGES ON loginAuth.* TO 'nawaz'@'localhost';
