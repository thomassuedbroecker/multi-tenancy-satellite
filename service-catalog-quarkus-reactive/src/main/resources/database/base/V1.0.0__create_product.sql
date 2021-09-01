CREATE SEQUENCE product_id_seq;
SELECT setval('product_id_seq', 3);
CREATE TABLE product
(
  id   INT,
  price DECIMAL(14,2),
  name VARCHAR(100),
  description VARCHAR(200),
  image VARCHAR(100)
);

INSERT INTO product (id,price,name,description,image) VALUES (1, 29.99, "Return of the Jedi", "Episode 6, Luke has the final confrontation with his father!", "images/Return.jpg"); 
INSERT INTO product (id,price,name,description,image) VALUES (2, 29.99, "Empire Strikes Back", "Episode 5, Luke finds out a secret that will change his destiny", "images/Empire.jpg");
INSERT INTO product (id,price,name,description,image) VALUES (3, 29.99, "New Hope", "Episode 4, after years of oppression, a band of rebels fight for freedom", "images/NewHope.jpg");
INSERT INTO product (id,price,name,description,image) VALUES (4, 100.00, "DVD Player", "This Sony Player has crystal clear picture", "images/Player.jpg");
INSERT INTO product (id,price,name,description,image) VALUES (5, 200.00, "BlackBerry Curve", "This BlackBerry offers rich PDA functions and works with Notes.", "images/BlackBerry.jpg");
INSERT INTO product (id,price,name,description,image) VALUES (6, 150.00, "Sony Ericsson", "This Sony phone takes pictures and plays Music.", "images/SonyPhone.jpg");
INSERT INTO product (id,price,name,description,image) VALUES (6, 1800.00, "Sony Bravia", "This is a 40 inch 1080p LCD HDTV", "images/SonyTV.jpg");
INSERT INTO product (id,price,name,description,image) VALUES (7, 1150.00, "Sharp Aquos", "This is 32 inch 1080p LCD HDTV", "images/SamTV.jpg");
INSERT INTO product (id,price,name,description,image) VALUES (8, 20.00,  "Go Fish: Superstar", "Go Fish release their great new hit, Superstar", "images/Superstar.jpg");
INSERT INTO product (id,price,name,description,image) VALUES (9, 20.00, "Ludwig van Beethoven", "This is a classic, the 9 Symphonies Box Set", "images/Bet.jpg");
INSERT INTO product (id,price,name,description,image) VALUES (10, 399.99, "PlayStation 3", "Brace yourself for the marvels of the PlayStation 3 complete with 80GB hard disk storage", "images/PS3.jpg");
INSERT INTO product (id,price,name,description,image) VALUES (11, 169.99, "Nintendo Wii", "Next-generation gaming with a motion-sensitive controller", "images/wii.jpg");
INSERT INTO product (id,price,name,description,image) VALUES (12, 299.99, "XBOX 360", "Expand your horizons with the gaming and multimedia capabilities of this incredible system", "images/xbox360.jpg");  
