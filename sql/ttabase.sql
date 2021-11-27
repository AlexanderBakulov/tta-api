DROP DATABASE IF EXISTS `ttabase2`;
CREATE DATABASE `ttabase2`;
USE `ttabase2`;

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
INSERT INTO `role` (name) VALUES ('ROLE_MANAGER');
INSERT INTO `role` (name) VALUES ('ROLE_SUPPORT');
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





--CREATE TABLE `ticket` (
--id INT(64) NOT NULL AUTO_INCREMENT,
--subject VARCHAR(50) NOT NULL,
--priority ENUM('URGENT', 'CRITICAL', 'MEDIUM', 'LOW') NOT NULL,
--current_priority ENUM('URGENT', 'CRITICAL', 'MEDIUM', 'LOW') NOT NULL,
--message VARCHAR(100) NOT NULL,
--creator_id INT(64) NOT NULL,
--status ENUM('CREATED', 'QUEUED', 'IN_WORK', 'REPORTED_AS_DONE', 'CONFIRMED', 'REJECTED', 'SUPPORT_REJECT', 'HOLD') NOT NULL,
--created DATETIME NOT NULL,
--in_work DATETIME DEFAULT NULL,
--executor_id INT(64) DEFAULT NULL,
--done DATETIME DEFAULT NULL,
--confirmed DATETIME DEFAULT NULL,
--rejected DATETIME DEFAULT NULL,
--support_rejected DATETIME DEFAULT NULL,
--support_reject_cause VARCHAR(100) DEFAULT NULL,
--start_hold DATETIME DEFAULT NULL,
--end_hold DATETIME DEFAULT NULL,
--hold_cause VARCHAR(100) DEFAULT NULL,
--PRIMARY KEY (id),
--KEY creator_id (creator_id),
--KEY executor_id (executor_id),
--KEY priority (priority),
--KEY status (status),
--FOREIGN KEY (creator_id) REFERENCES user (id),
--FOREIGN KEY (executor_id) REFERENCES user (id)
--) ENGINE=INNODB DEFAULT CHARSET=utf8;
--
--
--CREATE TABLE `event` (
--id INT(64) NOT NULL AUTO_INCREMENT,
--ticket_id INT(64) NOT NULL,
--status ENUM('CREATED', 'QUEUED', 'IN_WORK', 'REPORTED_AS_DONE', 'CONFIRMED', 'REJECTED', 'SUPPORT_REJECT', 'HOLD') NOT NULL,
--start_time DATETIME DEFAULT NULL,
--end_time DATETIME DEFAULT NULL,
--PRIMARY KEY (id),
--KEY ticket_id (ticket_id),
--KEY status (status),
--FOREIGN KEY (ticket_id) REFERENCES ticket (id)
--) ENGINE=INNODB DEFAULT CHARSET=utf8;
