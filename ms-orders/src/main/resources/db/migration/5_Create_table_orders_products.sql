CREATE TABLE IF NOT EXISTS order_products_tb (
    principal_class_id INT UNSIGNED,
    product_id INT,
    quantity INT,
    PRIMARY KEY (principal_class_id, product_id),
    FOREIGN KEY (principal_class_id) REFERENCES order_tb (id)
);

INSERT INTO order_products_tb (principal_class_id, product_id, quantity)
VALUES
  (3, 3, 3),
  (4, 6, 5),
  (1, 7, 6),
  (1, 3, 4),
  (3, 7, 2),
  (8, 4, 1),
  (9, 3, 8),
  (4, 4, 5),
  (5, 7, 7),
  (7, 9, 9),
  (6, 8, 6),
  (9, 5, 2),
  (2, 8, 1),
  (5, 4, 4),
  (6, 8, 6),
  (7, 9, 2),
  (8, 4, 3),
  (9, 6, 1),
  (10, 1, 5);