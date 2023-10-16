CREATE TABLE IF NOT EXISTS order_tb (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    foreign_address_id INT UNSIGNED NOT NULL,
    payment_method VARCHAR(255),
    subtotal_value DOUBLE,
    discount DOUBLE,
    total_value DOUBLE,
    create_date TIMESTAMP,
    status VARCHAR(255),
    cancel_reason VARCHAR(255),
    cancel_date TIMESTAMP,
    FOREIGN KEY (foreign_address_id) REFERENCES address_tb (id)
);

INSERT INTO order_tb (foreign_address_id, payment_method, subtotal_value, discount, total_value, create_date, status, cancel_reason, cancel_date)
VALUES
    (1, 'CREDIT_CARD', 100.0, 10.0, 90.0, '2023-10-14 10:30:00', 'CANCELED', 'Out of stock', '2023-10-15 09:00:00'),
    (2, 'CRYPTOCURRENCY', 75.0, 5.0, 70.0, '2023-10-14 09:15:00', 'CONFIRMED', '', NULL),
    (3, 'PIX', 50.0, 0.0, 50.0, '2023-10-13 15:45:00', 'CANCELED', 'Address not found', '2023-10-14 08:30:00'),
    (4, 'CREDIT_CARD', 120.0, 15.0, 105.0, '2023-10-12 14:20:00', 'CONFIRMED', '', NULL),
    (5, 'CRYPTOCURRENCY', 90.0, 10.0, 80.0, '2023-10-11 12:30:00', 'CANCELED', 'Out of stock', '2023-10-12 11:45:00'),
    (1, 'PIX', 110.0, 0.0, 110.0, '2023-10-10 11:15:00', 'CONFIRMED', '', NULL),
    (3, 'CREDIT_CARD', 70.0, 5.0, 65.0, '2023-10-09 08:30:00', 'CANCELED', 'Payment issue', '2023-10-10 07:45:00'),
    (2, 'GIFT_CARD', 60.0, 5.0, 55.0, '2023-10-08 10:00:00', 'CONFIRMED', '', NULL),
    (5, 'PIX', 85.0, 10.0, 75.0, '2023-10-07 09:30:00', 'CONFIRMED', '', NULL),
    (4, 'CREDIT_CARD', 95.0, 0.0, 95.0, '2023-10-06 14:10:00', 'CONFIRMED', '', NULL);