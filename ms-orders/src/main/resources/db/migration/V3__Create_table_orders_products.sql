CREATE TABLE IF NOT EXISTS order_products_tb (
    principal_class_id BIGINT,
    product_id BIGINT,
    quantity INT,
    PRIMARY KEY (principal_class_id, product_id),
    FOREIGN KEY (principal_class_id) REFERENCES order_tb (id)
);

INSERT INTO order_products_tb (principal_class_id, product_id, quantity)
VALUES
  (3, 3, 3),
  (4, 6, 5),
  (1, 7, 6),
  (1, 5, 4),
  (3, 2, 2),
  (8, 8, 1),
  (9, 1, 8),
  (4, 10, 5),
  (5, 4, 7),
  (7, 9, 9)