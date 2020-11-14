CREATE DATABASE IF NOT EXISTS olskercupcakes;
CREATE USER IF NOT EXISTS 'olskercupcakes'@'localhost';
GRANT ALL PRIVILEGES on olskercupcakes.* to 'olskercupcakes'@'localhost';

CREATE TABLE IF NOT EXISTS user (
    id int PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(60) UNIQUE,
    password VARCHAR(64),
    balance int,
    isAdmin tinyint(1)
);

CREATE TABLE IF NOT EXISTS cupcakeTops(
    id INT PRIMARY KEY AUTO_INCREMENT,
    ingredient VARCHAR(60),
    price INT
);

INSERT INTO cupcakeTops (ingredient, price) VALUES ('mascarpone', 12);
INSERT INTO cupcakeTops (ingredient, price) VALUES ('nødder', 20);


CREATE TABLE IF NOT EXISTS cupcakeBottoms(
id INT PRIMARY KEY AUTO_INCREMENT,
ingredient VARCHAR(60),
price INT
);
INSERT INTO cupcakeBottoms(ingredient, price) VALUES ('kagebund', 15);
INSERT INTO cupcakeBottoms (ingredient, price) VALUES ('nøddebund', 25);


