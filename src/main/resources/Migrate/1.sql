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
                                         price double not null,
                                         height int,
                                         type ENUM ('Træ',
                                             'Tagplade',
                                             'Tagsten',
                                             'Beslag & Skruer',
                                             'Andet') NOT NULL,
                                         width int,
                                         overlap int default 0
);

INSERT INTO materials(name, color, price, height, type, width,overlap) VALUES('Plastmo', 'blåtonet', 60.0, 10, 'Tagplade',1090, 100);
INSERT INTO materials(name, color, price, height, type, width,overlap) VALUES('Plastmo', 'klar', 60.0, 10, 'Tagplade',1090, 100);
INSERT INTO materials(name, color, price, height, type, width,overlap) VALUES('Plastmo', 'jumboLite', 60.0, 10, 'Tagplade',1090, 100 );
/*TODO det er 14,6 stk pr kvm. og 3 lbm (løbende metre) (nogle af priserne har vi selv sat)*/
INSERT INTO materials(name, color, price, height, type, width,overlap) VALUES('Betontagsten B&C', 'sort', 17.97, 30, 'Tagsten', 200,20);
INSERT INTO materials(name, color, price, height, type, width,overlap) VALUES('Betonrygsten B&C', 'sort', 170.97, 50, 'Tagsten', 200,20);
INSERT INTO materials(name, color, price, height, type, width,overlap) VALUES('Erternitagsten B6', 'teglrøde', 16.97, 30, 'Tagsten', 200,20);
INSERT INTO materials(name, color, price, height, type, width,overlap) VALUES('Erternitrygsten B6', 'teglrøde', 160.97, 50, 'Tagsten', 200,20);
INSERT INTO materials(name, color, price, height, type, width,overlap) VALUES('Erternittagsten B7', 'mokka(brun)', 14.97, 30, 'Tagsten', 200,20);
INSERT INTO materials(name, color, price, height, type, width,overlap) VALUES('Erternitrygsten B7', 'mokka(brun)', 140.97, 50, 'Tagsten', 200,20);
INSERT INTO materials(name, color, price, height, type, width,overlap) VALUES('Tagsten Volstrup', 'røde', 15.97, 30, 'Tagsten', 200,20);
INSERT INTO materials(name, color, price, height, type, width,overlap) VALUES('Rygsen Volstrup', 'røde', 150.97, 50, 'Tagsten', 200,20);
INSERT INTO materials(name, color, price, height, type, width,overlap) VALUES('Tagsten Vinge', 'røde', 17.97, 30, 'Tagsten', 200,20);
INSERT INTO materials(name, color, price, height, type, width,overlap) VALUES('Rygsten Vinge', 'røde', 170.97, 50, 'Tagsten', 200,20);
/*TODO pris er pr. m*/
INSERT INTO materials(name, color, price, height, type, width,overlap) VALUES('trykimp. Stolpe', 'lys birk', 41.89, 97, 'Træ',97,0);
INSERT INTO materials(name, color, price, height, type, width,overlap) VALUES('trykimp. Bræt', 'lys birk', 22.45, 19, 'Træ',100,0);
INSERT INTO materials(name, color, price, height, type, width,overlap) VALUES('trykimp. Bræt', 'lys birk', 33.99, 25, 'Træ', 150,0);
INSERT INTO materials(name, color, price, height, type, width,overlap) VALUES('reglar ubh.', 'lys birk', 20.29, 45, 'Træ', 95,0);
INSERT INTO materials(name, color, price, height, type, width,overlap) VALUES('taglægte T1', 'lys birk', 30.29, 38, 'Træ', 73,0);
INSERT INTO materials(name, color, price, height, type, width,overlap) VALUES('spærtræ ubh.', 'lys birk', 23.29, 45, 'Træ', 195,0);
INSERT INTO materials(name, color, price, height, type, width,overlap) VALUES('Færdigskåret (byg-selv-spær) 8 stk.', 'lys birk', 20.29, 30, 'Træ',null,0);
INSERT INTO materials(name, color, price, height, type, width,overlap) VALUES('Lægte ubh.', 'lys birk', 20.29, 38, 'Træ',73,0);

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
INSERT INTO materials_By_Category(materialID, categoryID) VALUES(14,4);
INSERT INTO materials_By_Category(materialID, categoryID) VALUES(15,2);
INSERT INTO materials_By_Category(materialID, categoryID) VALUES(15,5);
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
    /*TODO lagerBeholdning int,*/
                                        describtion varchar(100),
                                        quantity int not null,
                                        price double default 0.0
);
