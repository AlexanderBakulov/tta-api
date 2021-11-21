INSERT INTO `user` (login, password, email, first_name, last_name, office_id, division_id)
VALUES ('user', '', 'user@tta.com', 'Ivan', 'Ivanov', 1, 1);

INSERT INTO `user` (login, password, email, first_name, last_name, office_id, division_id)
VALUES ('manager', '', 'boss@tta.com', 'Angry', 'Boss', 1, 1);

INSERT INTO `user` (login, password, email, first_name, last_name, office_id, division_id, executor, ticket_counter)
VALUES ('support1', '', 'support1@tta.com', 'Petr', 'Ivanov', 1, 1, true, 3);

INSERT INTO `user` (login, password, email, first_name, last_name, office_id, division_id, executor, ticket_counter)
VALUES ('support2', '', 'support2@tta.com', 'Petr', 'Petrov', 1, 1, true, 1);

INSERT INTO `user` (login, password, email, first_name, last_name, office_id, division_id, executor, ticket_counter)
VALUES ('support3', '', 'support3@tta.com', 'Petr', 'Sidorov', 1, 1, true, 2);

INSERT INTO `user` (login, password, email, first_name, last_name, office_id, division_id, executor, ticket_counter)
VALUES ('support4', '', 'support4@tta.com', 'Boom', 'Box', 2, 1, true, 0);

INSERT INTO `user_role` (user_id, role_id) VALUES (4, 3);
INSERT INTO `user_role` (user_id, role_id) VALUES (5, 3);
INSERT INTO `user_role` (user_id, role_id) VALUES (6, 3);
INSERT INTO `user_role` (user_id, role_id) VALUES (7, 3);
