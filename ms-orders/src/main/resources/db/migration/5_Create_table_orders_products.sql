CREATE TABLE IF NOT EXISTS order_products_tb (
    principal_class_id INT UNSIGNED,
    product_id INT,
    quantity INT,
    PRIMARY KEY (principal_class_id, product_id),
    FOREIGN KEY (principal_class_id) REFERENCES order_tb (id)
);