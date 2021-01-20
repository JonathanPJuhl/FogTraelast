
DROP TABLE IF EXISTS lengths;
DROP TABLE IF EXISTS widths;

CREATE TABLE IF NOT EXISTS lengths(
                                      length int PRIMARY KEY NOT NULL,
                                      price double NOT NULL
);
INSERT INTO lengths(length, price) VALUES(3000, 70.00);
INSERT INTO lengths(length, price) VALUES(6000, 120.00);

CREATE TABLE IF NOT EXISTS widths(
                                     width int PRIMARY KEY NOT NULL,
                                     price double NOT NULL
);

INSERT INTO widths(width, price) VALUES(2900, 100.00);
INSERT INTO widths(width, price) VALUES(1900, 90.00);

UPDATE properties
SET value = '4'
WHERE name = 'version';