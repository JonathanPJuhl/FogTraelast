DROP TABLE IF EXISTS bom;
DROP TABLE IF EXISTS orders;
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

UPDATE properties
SET value = '1'
WHERE name = 'version';