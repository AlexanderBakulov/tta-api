DROP DATABASE IF EXISTS `test-ttabase2`;
CREATE DATABASE `test-ttabase2`;
USE `test-ttabase2`;

DROP TABLE IF EXISTS `office`;

CREATE TABLE `office` (
id INT NOT NULL AUTO_INCREMENT,
name VARCHAR(5) NOT NULL,
time_zone SMALLINT DEFAULT 0,
PRIMARY KEY (id),
UNIQUE KEY name (name)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

INSERT INTO `office` (name, time_zone) VALUES ('MSK', 3);
INSERT INTO `office` (name, time_zone) VALUES ('SPB', 3);
INSERT INTO `office` (name, time_zone) VALUES ('NSK', 7);

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
id INT NOT NULL AUTO_INCREMENT,
login VARCHAR(32) NOT NULL,
password VARCHAR(64) NOT NULL,
temp BOOLEAN NOT NULL DEFAULT TRUE,
email VARCHAR(64) NOT NULL,
first_name VARCHAR(32) NOT NULL,
last_name VARCHAR(32) NOT NULL,
active BOOLEAN NOT NULL DEFAULT TRUE,
role VARCHAR(16) DEFAULT NULL,
office_id INT,
PRIMARY KEY (id),
UNIQUE KEY login (login),
UNIQUE KEY email (email),
KEY first_name (first_name),
KEY last_name (last_name),
FOREIGN KEY (office_id) REFERENCES office (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

INSERT INTO `user` (login, password, email, first_name, last_name, role, office_id)
VALUES ('admin', 'pass', 'admin@tta.com', 'admin', 'admin', 'ADMIN', 1);

DROP TABLE IF EXISTS `options`;

CREATE TABLE `options` (
id INT NOT NULL AUTO_INCREMENT,
free BOOLEAN DEFAULT TRUE,
on_duty BOOLEAN DEFAULT FALSE,
ticket_counter INT DEFAULT 0,
reject_counter INT DEFAULT 0,
user_id INT,
PRIMARY KEY (id),
FOREIGN KEY (user_id) REFERENCES user (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;