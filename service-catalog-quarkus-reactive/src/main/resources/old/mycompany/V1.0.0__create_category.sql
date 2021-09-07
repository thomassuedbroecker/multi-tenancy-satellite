CREATE SEQUENCE category_id_seq;
SELECT setval('category_id_seq', 8);
CREATE TABLE category
(
  id   INT,
  name VARCHAR(40),
  parent INT 
);

INSERT INTO category (id, name, parent) VALUES (1, "Entertainment", 0); 
INSERT INTO category (id, name, parent) VALUES (2, "Movies", 1); 
INSERT INTO category (id, name, parent) VALUES (3, "Music", 1); 
INSERT INTO category (id, name, parent) VALUES (4, "Games", 1); 
INSERT INTO category (id, name, parent) VALUES (5, "Electronics", 0); 
INSERT INTO category (id, name, parent) VALUES (6, "TV", 5); 
INSERT INTO category (id, name, parent) VALUES (7, "CellPhones", 5); 
INSERT INTO category (id, name, parent) VALUES (8, "DVD Players", 5); 
