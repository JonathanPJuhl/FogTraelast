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
INSERT INTO materials(name, color, price, height, type) VALUES('Plastmo', 'jumboLite', 60.0, 10, 'Tagplade');/*TODO det er 14,6 stk pr kvm. og 3 lbm (løbende metre) (nogle af priserne har vi selv sat)*/
INSERT INTO materials(name, color, price, height, type) VALUES('Betontagsten B&C', 'sort', 17.97, 30, 'Tagsten');
INSERT INTO materials(name, color, price, height, type) VALUES('Betonrygsten B&C', 'sort', 170.97, 50, 'Tagsten');
INSERT INTO materials(name, color, price, height, type) VALUES('Erternitagsten B6', 'teglrøde', 16.97, 30, 'Tagsten');
INSERT INTO materials(name, color, price, height, type) VALUES('Erternitrygsten B6', 'teglrøde', 160.97, 50, 'Tagsten');
INSERT INTO materials(name, color, price, height, type) VALUES('Erternittagsten B7', 'mokka(brun)', 14.97, 30, 'Tagsten');
INSERT INTO materials(name, color, price, height, type) VALUES('Erternitrygsten B7', 'mokka(brun)', 140.97, 50, 'Tagsten');
INSERT INTO materials(name, color, price, height, type) VALUES('Tagsten Volstrup', 'røde', 15.97, 30, 'Tagsten');
INSERT INTO materials(name, color, price, height, type) VALUES('Rygsen Volstrup', 'røde', 150.97, 50, 'Tagsten');
INSERT INTO materials(name, color, price, height, type) VALUES('Tagsten Vinge', 'røde', 17.97, 30, 'Tagsten');
INSERT INTO materials(name, color, price, height, type) VALUES('Rygsten Vinge', 'røde', 170.97, 50, 'Tagsten');/*TODO pris er pr. m*/
INSERT INTO materials(name, color, price, height, type) VALUES('trykimp. Stolpe', 'lys birk', 41.89, 97, 'Træ');
INSERT INTO materials(name, color, price, height, type) VALUES('trykimp. Bræt', 'lys birk', 22.45, 19, 'Træ');
INSERT INTO materials(name, color, price, height, type) VALUES('trykimp. Bræt', 'lys birk', 33.99, 25, 'Træ');
INSERT INTO materials(name, color, price, height, type) VALUES('reglar ubh.', 'lys birk', 20.29, 45, 'Træ');
INSERT INTO materials(name, color, price, height, type) VALUES('taglægte T1', 'lys birk', 30.29, 38, 'Træ');
INSERT INTO materials(name, color, price, height, type) VALUES('spærtræ ubh.', 'lys birk', 23.29, 45, 'Træ');
INSERT INTO materials(name, color, price, height, type) VALUES('Færdigskåret (byg-selv-spær) 8 stk.', 'lys birk', 20.29, 30, 'Træ');
INSERT INTO materials(name, color, price, height, type) VALUES('Lægte ubh.', 'lys birk', 20.29, 45, 'Træ');

CREATE TABLE IF NOT EXISTS materials_By_Category(
                                                    materials_CategoryID int PRIMARY KEY AUTO_INCREMENT,
                                                    categoryID int NOT NULL,
                                                    FOREIGN KEY (categoryID) REFERENCES categories(categoryID),
                                                    materialID int NOT NULL ,
                                                    FOREIGN KEY (materialID) REFERENCES materials(materialID)
);

/*Tagsten*/
INSERT INTO materials_By_Category(materialID, categoryID) VALUES(4,2);
INSERT INTO materials_By_Category(materialID, categoryID) VALUES(5,2);
INSERT INTO materials_By_Category(materialID, categoryID) VALUES(6,2);
INSERT INTO materials_By_Category(materialID, categoryID) VALUES(7,2);
INSERT INTO materials_By_Category(materialID, categoryID) VALUES(8,2);
INSERT INTO materials_By_Category(materialID, categoryID) VALUES(9,2);
INSERT INTO materials_By_Category(materialID, categoryID) VALUES(10,2);
INSERT INTO materials_By_Category(materialID, categoryID) VALUES(11,2);
INSERT INTO materials_By_Category(materialID, categoryID) VALUES(12,2);
INSERT INTO materials_By_Category(materialID, categoryID) VALUES(13,2);

/*Ifølge tag med rejsning og skur skitse*/
INSERT INTO materials_By_Category(materialID, categoryID) VALUES(16,2);
INSERT INTO materials_By_Category(materialID, categoryID) VALUES(16,3);
INSERT INTO materials_By_Category(materialID, categoryID) VALUES(16,4);
INSERT INTO materials_By_Category(materialID, categoryID) VALUES(20,2);
INSERT INTO materials_By_Category(materialID, categoryID) VALUES(14,3);
INSERT INTO materials_By_Category(materialID, categoryID) VALUES(19,3);
INSERT INTO materials_By_Category(materialID, categoryID) VALUES(19,4);
INSERT INTO materials_By_Category(materialID, categoryID) VALUES(17,4);
INSERT INTO materials_By_Category(materialID, categoryID) VALUES(15,2);
INSERT INTO materials_By_Category(materialID, categoryID) VALUES(15,5);
INSERT INTO materials_By_Category(materialID, categoryID) VALUES(15,4);
INSERT INTO materials_By_Category(materialID, categoryID) VALUES(16,2);
INSERT INTO materials_By_Category(materialID, categoryID) VALUES(18,4);
INSERT INTO materials_By_Category(materialID, categoryID) VALUES(18,2);

/*Tagplader*/
INSERT INTO materials_By_Category(materialID, categoryID) VALUES(1,1);
INSERT INTO materials_By_Category(materialID, categoryID) VALUES(2,1);
INSERT INTO materials_By_Category(materialID, categoryID) VALUES(3,1);

/*Ifølge fladt tag skitse uden dubletter*/
INSERT INTO materials_By_Category(materialID, categoryID) VALUES(16,1);
INSERT INTO materials_By_Category(materialID, categoryID) VALUES(21,4);
INSERT INTO materials_By_Category(materialID, categoryID) VALUES(19,1);

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
                                        price double default 0.0
);
