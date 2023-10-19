CREATE TABLE IF NOT EXISTS address_tb (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    street VARCHAR(255),
    number INT,
    complement VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(255),
    postal_code VARCHAR(255)
);

INSERT INTO address_tb (street, number, complement, city, state, postal_code) VALUES
    ('123 Main St', 10, 'Apt 3B', 'Cityville', 'CA', '12345'),
    ('456 Elm St', 25, 'Unit 6', 'Townsville', 'NY', '54321'),
    ('789 Oak St', 17, 'Suite 2C', 'Villageburg', 'TX', '98765'),
    ('101 Pine St', 30, 'Apt 5D', 'Hamletville', 'FL', '67890'),
    ('555 Cedar St', 8, 'Unit 1A', 'Citytown', 'IL', '34567'),
    ('222 Birch St', 42, 'Suite 4B', 'Towertown', 'OH', '23456'),
    ('333 Maple St', 14, 'Apt 7E', 'Villagetown', 'WA', '78901'),
    ('777 Walnut St', 19, 'Unit 3F', 'Metropolis', 'GA', '45678'),
    ('999 Oak St', 7, 'Suite 9A', 'Smalltown', 'NC', '12345'),
    ('444 Elm St', 22, 'Apt 2C', 'Countryside', 'PA', '56789');