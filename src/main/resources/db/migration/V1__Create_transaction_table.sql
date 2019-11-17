CREATE TABLE IF NOT EXISTS `TRANSACTION` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `user_id` BIGINT NOT NULL,
    `amount_buy` DECIMAL(19,2) NOT NULL,
    `amount_sell` DECIMAL(19,2) NOT NULL,
    `currency_from` VARCHAR(3) NOT NULL,
    `currency_to` VARCHAR(3) NOT NULL,
    `originating_country` VARCHAR(2) NOT NULL,
    `rate` DOUBLE PRECISION,
    `time_placed` DATETIME NOT NULL
);
