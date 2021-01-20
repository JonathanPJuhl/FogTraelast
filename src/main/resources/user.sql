Drop DATABASE fogtraelast;
CREATE DATABASE IF NOT EXISTS fogtraelast;
USE fogtraelast;
CREATE USER IF NOT EXISTS 'fogtraelast'@'localhost';
GRANT ALL PRIVILEGES on fogtraelast.* to 'fogtraelast'@'localhost';