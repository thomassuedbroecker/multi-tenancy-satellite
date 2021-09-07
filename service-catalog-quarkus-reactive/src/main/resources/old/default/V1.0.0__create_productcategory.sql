CREATE SEQUENCE product_id_seq;
SELECT setval('product_id_seq', 3);
CREATE TABLE productcategory
(
  id   INT,
  productid DECIMAL(14,2),
  categoryid DECIMAL(14,2)
);

INSERT INTO productcategory (id,productid,categoryid) VALUES (1, 1, 2); 
INSERT INTO productcategory (id,productid,categoryid) VALUES (2, 2, 2); 
INSERT INTO productcategory (id,productid,categoryid) VALUES (3, 3, 2); 
INSERT INTO productcategory (id,productid,categoryid) VALUES (4, 10, 8); 
INSERT INTO productcategory (id,productid,categoryid) VALUES (6, 20, 7); 
INSERT INTO productcategory (id,productid,categoryid) VALUES (7, 21, 7); 
INSERT INTO productcategory (id,productid,categoryid) VALUES (8, 30, 6); 
INSERT INTO productcategory (id,productid,categoryid) VALUES (9, 31, 6); 
INSERT INTO productcategory (id,productid,categoryid) VALUES (10, 40, 4);
INSERT INTO productcategory (id,productid,categoryid) VALUES (11, 41, 3);  
INSERT INTO productcategory (id,productid,categoryid) VALUES (21, 51, 4); 
INSERT INTO productcategory (id,productid,categoryid) VALUES (22, 52, 4); 
INSERT INTO productcategory (id,productid,categoryid) VALUES (23, 53, 4); 
