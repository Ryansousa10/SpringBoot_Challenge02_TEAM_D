CREATE TABLE IF NOT EXISTS feedback_tb (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    scale VARCHAR(255),
    comment VARCHAR(255),
    order_id BIGINT,
    FOREIGN KEY (order_id) REFERENCES order_tb (id)
);

INSERT INTO feedback_tb (scale, comment, order_id) VALUES
                        ("VERY_DISSATISFIED", "this is not good", 1),
                        ("DISSATISFIED", "i ok", 2),
                        ("NEUTRAL", "i don't know", 3),
                        ("SATISFIED", "this so good", 4),
                        ("VERY_SATISFIED", "this is very good", 5),
                        ("VERY_DISSATISFIED", "ridiculous", 6),
                        ("DISSATISFIED", "i ok", 7),
                        ("NEUTRAL", "i don't know", 8),
                        ("SATISFIED", "this is not good", 9),
                        ("VERY_DISSATISFIED", "this is not good", 10);
