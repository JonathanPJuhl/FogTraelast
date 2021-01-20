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
                                     wallsOrNo tinyint(1) NOT NULL,
                                     shedLength int,
                                     shedWidth int,
                                     svg BLOB,
                                     salesmanID int,
                                     FOREIGN KEY (salesmanID) REFERENCES salesmen(salesmanID)
);
INSERT INTO orders (length, width, customerPhone, customerEmail, roofType, shedOrNo, wallsOrNo, shedLength, shedWidth) VALUES(500, 500, '+4512345678', 'test@test.dk', 'Pitched', 1,1, 1000, 1000);
INSERT INTO orders (length, width, customerPhone, customerEmail, roofType, shedOrNo, wallsOrNo, shedLength, shedWidth) VALUES(600, 600, '12345678', 'test2@test.dk','Flat',0,0, 1000, 1000);

UPDATE properties
SET value = '2'
WHERE name = 'version';