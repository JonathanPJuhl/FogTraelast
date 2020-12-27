Drop DATABASE fogtraelast;
CREATE DATABASE IF NOT EXISTS fogtraelast;
USE fogtraelast;
CREATE USER IF NOT EXISTS 'fogtraelast'@'localhost';
GRANT ALL PRIVILEGES on fogtraelast.* to 'fogtraelast'@'localhost';

DROP TABLE IF EXISTS salesmen;

CREATE TABLE IF NOT EXISTS salesmen(
                                       salesmanID int PRIMARY KEY AUTO_INCREMENT,
                                       name varchar (70) NOT NULL,
                                       phone varchar(45) NOT NULL,
                                       email varchar(70) NOT NULL,
                                       password varchar(70) NOT NULL,
                                       role int
);
INSERT INTO salesmen (name, phone, email, password, role) VALUES('bo','+4512345678', 'test@test.dk', '1234', 2);
INSERT INTO salesmen (name, phone, email, password, role) VALUES('ib', '12345678', 'test2@test.dk', '56789', 2);

DROP TABLE IF EXISTS orders;

CREATE TABLE IF NOT EXISTS orders(
    orderID int PRIMARY KEY AUTO_INCREMENT,
    orderStatus  ENUM ('New', 'Processing','Done') DEFAULT 'New',
    length FLOAT NOT NULL,
    width FLOAT NOT NULL,
    customerPhone varchar(45) NOT NULL,
    customerEmail varchar(70) NOT NULL,
    price float,
    roofType ENUM('Pitched', 'Flat') DEFAULT 'Flat' NOT NULL,
    shedOrNo tinyint(1) NOT NULL,
    cladding tinyint(1) NOT NULL,
    salesmanID int, /*TODO kunne dette være en role istedet? Lige nu overholder vi ikke første normalform f.eks. Det ville vi da ikke komme til regardless?*/
    FOREIGN KEY (salesmanID) REFERENCES salesmen(salesmanID)
);
INSERT INTO orders (length, width, customerPhone, customerEmail, roofType, shedOrNo, cladding) VALUES(500, 500, '+4512345678', 'test@test.dk', 'Pitched', 1,1);
INSERT INTO orders (length, width, customerPhone, customerEmail, shedOrNo, cladding) VALUES(600, 600, '12345678', 'test2@test.dk', 0,0);

