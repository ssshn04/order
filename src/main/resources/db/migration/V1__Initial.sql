CREATE TABLE t_orders
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_date  TIMESTAMP      NOT NULL,
    status      VARCHAR(50)    NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL
);
