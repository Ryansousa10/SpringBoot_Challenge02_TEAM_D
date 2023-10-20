CREATE TABLE `products_tb` (
  `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(255),
  `description` VARCHAR(255),
  `price` DOUBLE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;