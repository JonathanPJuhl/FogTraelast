USE fogtraelast;

DROP TABLE IF EXISTS materialTypes;
DROP TABLE IF EXISTS bom;
DROP TABLE IF EXISTS materials_By_Category;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS materials;


CREATE TABLE IF NOT EXISTS categories(
                                         categoryID int PRIMARY KEY AUTO_INCREMENT,
                                         category varchar(50) NOT NULL
);

INSERT INTO categories (category) VALUES('Flat');
INSERT INTO categories (category) VALUES('Pitched');
INSERT INTO categories (category) VALUES('Carport');
INSERT INTO categories (category) VALUES('Shed');
INSERT INTO categories (category) VALUES('Cladding');


CREATE TABLE IF NOT EXISTS materials(
                                         materialID int PRIMARY KEY AUTO_INCREMENT,
                                         name varchar(50) NOT NULL,
                                         color varchar(50),
                                         price double,
                                         height int,
                                         type ENUM ('Træ',
                                             'Tagplade',
                                             'Tagsten',
                                             'Beslag & Skruer',
                                             'Andet') NOT NULL
);


INSERT INTO materials(name, color, price, height, type) VALUES('Plastmo', 'blåtonet', 60.0, 10, 'Tagplade');
INSERT INTO materials(name, color, price, height, type) VALUES('Plastmo', 'klar', 60.0, 10, 'Tagplade');
INSERT INTO materials(name, color, price, height, type) VALUES('Plastmo3', 'blåtonet', 60.0, 10, 'Tagplade');
INSERT INTO materials(name, color, price, height, type) VALUES('Plastmo4', 'klar', 60.0, 10, 'Tagplade');





CREATE TABLE IF NOT EXISTS materials_By_Category(
                                                    materials_CategoryID int PRIMARY KEY AUTO_INCREMENT,
                                                    categoryID int NOT NULL,
                                                    FOREIGN KEY (categoryID) REFERENCES categories(categoryID),
                                                    materialID int NOT NULL ,
                                                    FOREIGN KEY (materialID) REFERENCES materials(materialID)
);

INSERT INTO materials_By_Category(materialID, categoryID) VALUES(1,1);
INSERT INTO materials_By_Category(materialID, categoryID) VALUES(2,1);
INSERT INTO materials_By_Category(materialID, categoryID) VALUES(3,2);
INSERT INTO materials_By_Category(materialID, categoryID) VALUES(4,2);


CREATE TABLE IF NOT EXISTS bom(
                                        bomID int PRIMARY KEY AUTO_INCREMENT,
                                        orderID int,
                                        FOREIGN KEY (orderID) REFERENCES orders(orderID),
                                        materialID int,
                                        FOREIGN KEY (materialID) REFERENCES materials(materialID),
                                        length int,
    /* TODO Cath længdetabel med 30 cm forskel mellem sat max og min længde
            angående trælængder*/
    /*TODO lagerBeholdning int,*/
                                        price double NOT NULL
);
