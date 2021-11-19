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

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
id INT(64) NOT NULL AUTO_INCREMENT,
login VARCHAR(30) NOT NULL,
password VARCHAR(68) NOT NULL,
temp BOOLEAN NOT NULL DEFAULT TRUE,
email VARCHAR(50) NOT NULL,
first_name VARCHAR(30) NOT NULL,
last_name VARCHAR(30) NOT NULL,
active BOOLEAN NOT NULL DEFAULT TRUE,
office_id INT(64),
executor BOOLEAN DEFAULT FALSE,
free BOOLEAN DEFAULT TRUE,
ticket_counter INT(11) DEFAULT 0,
reject_counter INT(11) DEFAULT 0,
PRIMARY KEY (id),
UNIQUE KEY login (login),
UNIQUE KEY email (email),
KEY first_name (first_name),
KEY last_name (last_name),
KEY executor (executor),
KEY free (free),
FOREIGN KEY (office_id) REFERENCES office (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO `user` (login, password, email, first_name, last_name, office_id)
VALUES ('admin', '1d8b9bdf745c1890fa6e03c4ea5675b944ffb8b49737f37b12a16e7f6881ee95', 'admin@tta.com', 'admin', 'admin', 1);

DROP TABLE IF EXISTS `session`;

CREATE TABLE `session` (
id INT(64) NOT NULL AUTO_INCREMENT,
token VARCHAR(36),
user_id INT(64) NOT NULL,
PRIMARY KEY (id),
UNIQUE KEY token (token),
FOREIGN KEY (user_id) REFERENCES user (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
id INT(64) NOT NULL AUTO_INCREMENT,
name VARCHAR(30) NOT NULL,
PRIMARY KEY (id),
UNIQUE KEY name (name)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO `role` (name) VALUES ('ADMIN');
INSERT INTO `role` (name) VALUES ('MANAGER');
INSERT INTO `role` (name) VALUES ('SUPPORT');
INSERT INTO `role` (name) VALUES ('USER');

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
user_id INT(64) NOT NULL,
role_id INT(64) NOT NULL,
PRIMARY KEY (user_id, role_id),
KEY user_id (user_id),
KEY role_id (role_id),
FOREIGN KEY (user_id) REFERENCES user (id),
FOREIGN KEY (role_id) REFERENCES role (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO `user_role` (user_id, role_id) VALUES (1, 1);

DROP TABLE IF EXISTS `ticket`;

CREATE TABLE `ticket` (
id INT(64) NOT NULL AUTO_INCREMENT,
subject VARCHAR(50) NOT NULL,
priority ENUM('URGENT', 'CRITICAL', 'MEDIUM', 'LOW') NOT NULL,
current_priority ENUM('URGENT', 'CRITICAL', 'MEDIUM', 'LOW') NOT NULL,
message VARCHAR(100) NOT NULL,
creator_id INT(64) NOT NULL,
status ENUM('CREATED', 'QUEUED', 'IN_WORK', 'REPORTED_AS_DONE', 'CONFIRMED', 'REJECTED', 'SUPPORT_REJECT', 'HOLD') NOT NULL,
created DATETIME NOT NULL,
in_work DATETIME DEFAULT NULL,
executor_id INT(64) DEFAULT NULL,
done DATETIME DEFAULT NULL,
confirmed DATETIME DEFAULT NULL,
rejected DATETIME DEFAULT NULL,
support_rejected DATETIME DEFAULT NULL,
support_reject_cause VARCHAR(100) DEFAULT NULL,
start_hold DATETIME DEFAULT NULL,
end_hold DATETIME DEFAULT NULL,
hold_cause VARCHAR(100) DEFAULT NULL,
PRIMARY KEY (id),
KEY creator_id (creator_id),
KEY executor_id (executor_id),
KEY priority (priority),
KEY status (status),
FOREIGN KEY (creator_id) REFERENCES user (id),
FOREIGN KEY (executor_id) REFERENCES user (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;


CREATE TABLE `event` (
id INT(64) NOT NULL AUTO_INCREMENT,
ticket_id INT(64) NOT NULL,
status ENUM('CREATED', 'QUEUED', 'IN_WORK', 'REPORTED_AS_DONE', 'CONFIRMED', 'REJECTED', 'SUPPORT_REJECT', 'HOLD') NOT NULL,
start_time DATETIME DEFAULT NULL,
end_time DATETIME DEFAULT NULL,
PRIMARY KEY (id),
KEY ticket_id (ticket_id),
KEY status (status),
FOREIGN KEY (ticket_id) REFERENCES ticket (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;