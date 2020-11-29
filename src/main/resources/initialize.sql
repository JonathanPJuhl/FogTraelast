CREATE DATABASE IF NOT EXISTS fogtraelast;
/*CREATE USER IF NOT EXISTS 'fogtraelast'@'localhost';*/
/*GRANT ALL PRIVILEGES on fogtraelast.* to 'fogtraelast'@'localhost';*/


DROP TABLE IF EXISTS orders;

CREATE TABLE IF NOT EXISTS orders(
    orderID int PRIMARY KEY AUTO_INCREMENT,
    orderStatus  ENUM ('New', 'Processing','Done')DEFAULT 'New',
    length FLOAT NOT NULL,
    width FLOAT NOT NULL,
    costumerPhone varchar(45) NOT NULL,
    customerEmail varchar(70) NOT NULL,
    price float,
    salesmanID int,
    FOREIGN KEY (salesmanID) REFERENCES salesmen(salesmanID)
);
INSERT INTO orders (length, width, costumerPhone, customerEmail) VALUES(500, 500, '+4512345678', 'test@test.dk');
INSERT INTO orders (length, width, costumerPhone, customerEmail) VALUES(600, 600, '12345678', 'test2@test.dk');

DROP TABLE IF EXISTS salesmen;

CREATE TABLE IF NOT EXISTS salesmen(
                                       salesmanID int PRIMARY KEY AUTO_INCREMENT,
                                       phone varchar(45) NOT NULL,
                                       email varchar(70) NOT NULL,
                                       password varchar(70) NOT NULL
);
INSERT INTO salesmen (phone, email, password) VALUES('+4512345678', 'test@test.dk', '1234');
INSERT INTO salesmen (phone, email, password) VALUES('12345678', 'test2@test.dk', '56789');