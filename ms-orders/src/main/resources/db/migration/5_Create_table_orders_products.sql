CREATE TABLE IF NOT EXISTS order_products_tb (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    quantity INT,
    principal_class_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (principal_class_id) REFERENCES order_tb (id)
);