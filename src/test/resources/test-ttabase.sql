DROP DATABASE IF EXISTS `test-ttabase2`;
CREATE DATABASE `test-ttabase2`;
USE `test-ttabase2`;

DROP TABLE IF EXISTS `office`;

CREATE TABLE `office` (
id INT(32) NOT NULL AUTO_INCREMENT,
name VARCHAR(5) NOT NULL,
time_zone INT(2) DEFAULT 0,
PRIMARY KEY (id),
UNIQUE KEY name (name)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO `office` (name, time_zone) VALUES ('MSK', 3);
INSERT INTO `office` (name, time_zone) VALUES ('SPB', 3);
INSERT INTO `office` (name, time_zone) VALUES ('NSK', 7);

DROP TABLE IF EXISTS `division`;

CREATE TABLE `division` (
id INT(32) NOT NULL AUTO_INCREMENT,
name VARCHAR(32) NOT NULL,
PRIMARY KEY (id),
UNIQUE KEY name (name)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO `division` (name) VALUES ('Support department');
INSERT INTO `division` (name) VALUES ('Consultation department');
INSERT INTO `division` (name) VALUES ('Accounting department');
INSERT INTO `division` (name) VALUES ('Management');

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
id INT(32) NOT NULL AUTO_INCREMENT,
name VARCHAR(32) NOT NULL,
PRIMARY KEY (id),
UNIQUE KEY name (name)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO `role` (name) VALUES ('ROLE_ADMIN');
INSERT INTO `role` (name) VALUES ('ROLE_SUPPORT');
INSERT INTO `role` (name) VALUES ('ROLE_MANAGER');
INSERT INTO `role` (name) VALUES ('ROLE_USER');

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
id INT(32) NOT NULL AUTO_INCREMENT,
login VARCHAR(32) NOT NULL,
password VARCHAR(68) NOT NULL,
temp BOOLEAN NOT NULL DEFAULT TRUE,
email VARCHAR(64) NOT NULL,
first_name VARCHAR(32) NOT NULL,
last_name VARCHAR(32) NOT NULL,
active BOOLEAN NOT NULL DEFAULT TRUE,
office_id INT(32),
division_id INT(32),
role_id INT(32),
free BOOLEAN DEFAULT TRUE,
ticket_counter INT(16) DEFAULT 0,
reject_counter INT(16) DEFAULT 0,
PRIMARY KEY (id),
UNIQUE KEY login (login),
UNIQUE KEY email (email),
KEY first_name (first_name),
KEY last_name (last_name),
KEY free (free),
FOREIGN KEY (office_id) REFERENCES office (id),
FOREIGN KEY (division_id) REFERENCES division (id),
FOREIGN KEY (role_id) REFERENCES role (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO `user` (login, password, email, first_name, last_name, office_id, division_id, role_id)
VALUES ('admin', 'pass', 'admin@tta.com', 'admin', 'admin', 1, 1, 1);
