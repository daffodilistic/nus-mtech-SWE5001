drop table if exists `user_account_tab`;
CREATE TABLE `user_account_tab`
(
    `id`          BIGINT(20) NOT NULL AUTO_INCREMENT,
    `email`       VARCHAR(255) NOT NULL,
    `username`        VARCHAR(255) NOT NULL,
    `password`    VARCHAR(255) NOT NULL,
    `loggon_i`    VARCHAR(255) NOT NULL,
       PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;