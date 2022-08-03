-- user_role
INSERT INTO user_role (user_role_id, user_role_name, user_role_description)
VALUES (1, 'A', 'admin');

INSERT INTO user_role (user_role_id, user_role_name, user_role_description)
VALUES (2, 'U', 'user');

-- quiz_user
INSERT INTO quiz_user (user_id, user_name, first_name, last_name, tel, email, password, profile, create_time, update_time, user_role_id)
VALUES (1, 'admin', 'Apisit', 'Noiluangchai', '0623439913', 'apiiisit@outlook.com', '207291B1419A721D4D18B23CFBF40EBB', null, sysdate, sysdate, 1);
--password san
INSERT INTO quiz_user (user_id, user_name, first_name, last_name, tel, email, password, profile, create_time, update_time, user_role_id)
VALUES (2, 'tong', 'Wipawee', 'Phattanarach', '0800546884', 'wipawee@outlook.com', '1B29518EE0E136EF49D2A6A54528BC44', null, sysdate, sysdate, 2);
--password user

-- category
INSERT INTO category (category_id, category_name, category_description, category_active, create_time, update_time)
VALUES (1, 'Basic math', null, true, sysdate, sysdate);

INSERT INTO category (category_id, category_name, category_description, category_active, create_time, update_time)
VALUES (2, 'Mobile Application Development', null, true, sysdate, sysdate);

-- quiz
INSERT INTO quiz (quiz_id, quiz_name, quiz_description, quiz_pass, number_of_question, quiz_start, quiz_end, quiz_active, quiz_password, create_time, update_time, category_id)
VALUES (1, 'Math 1', null, 50, 5, sysdate, sysdate, true, 123456, sysdate, sysdate, 1);

INSERT INTO quiz (quiz_id, quiz_name, quiz_description, quiz_pass, number_of_question, quiz_start, quiz_end, quiz_active, quiz_password, create_time, update_time, category_id)
VALUES (2, 'Mobile 1', null, 50, 5, sysdate, sysdate, true, 123456, sysdate, sysdate, 2);

-- question
INSERT INTO question (question_id, question_name, question_type, question_time, create_time, update_time, quiz_id)
VALUES (1, '1+1=?', 'S', 15, sysdate, sysdate, 1);

INSERT INTO choice (choice_id, choice_name, choice_correct, create_time, update_time, question_id)
VALUES (1, '1', false, sysdate, sysdate, 1);
INSERT INTO choice (choice_id, choice_name, choice_correct, create_time, update_time, question_id)
VALUES (2, '2', true, sysdate, sysdate, 1);
INSERT INTO choice (choice_id, choice_name, choice_correct, create_time, update_time, question_id)
VALUES (3, '3', false, sysdate, sysdate, 1);
INSERT INTO choice (choice_id, choice_name, choice_correct, create_time, update_time, question_id)
VALUES (4, '4', false, sysdate, sysdate, 1);

-- question
INSERT INTO question (question_id, question_name, question_type, question_time, create_time, update_time, quiz_id)
VALUES (2, '?+?=69', 'M', 15, sysdate, sysdate, 1);

INSERT INTO choice (choice_id, choice_name, choice_correct, create_time, update_time, question_id)
VALUES (5, '34+35', true, sysdate, sysdate, 2);
INSERT INTO choice (choice_id, choice_name, choice_correct, create_time, update_time, question_id)
VALUES (6, '32+37', true, sysdate, sysdate, 2);
INSERT INTO choice (choice_id, choice_name, choice_correct, create_time, update_time, question_id)
VALUES (7, '34+34', false, sysdate, sysdate, 2);
INSERT INTO choice (choice_id, choice_name, choice_correct, create_time, update_time, question_id)
VALUES (8, '69+1', false, sysdate, sysdate, 2);

-- question
INSERT INTO question (question_id, question_name, question_type, question_time, create_time, update_time, quiz_id)
VALUES (3, '2+2=?', 'S', 15, sysdate, sysdate, 1);

INSERT INTO choice (choice_id, choice_name, choice_correct, create_time, update_time, question_id)
VALUES (9, '4', true, sysdate, sysdate, 3);
INSERT INTO choice (choice_id, choice_name, choice_correct, create_time, update_time, question_id)
VALUES (10, '6', false, sysdate, sysdate, 3);
INSERT INTO choice (choice_id, choice_name, choice_correct, create_time, update_time, question_id)
VALUES (11, '-2', false, sysdate, sysdate, 3);
INSERT INTO choice (choice_id, choice_name, choice_correct, create_time, update_time, question_id)
VALUES (12, '2', false, sysdate, sysdate, 3);

-- question
INSERT INTO question (question_id, question_name, question_type, question_time, create_time, update_time, quiz_id)
VALUES (4, '5-5=?', 'S', 15, sysdate, sysdate, 1);

INSERT INTO choice (choice_id, choice_name, choice_correct, create_time, update_time, question_id)
VALUES (13, '0', true, sysdate, sysdate, 4);
INSERT INTO choice (choice_id, choice_name, choice_correct, create_time, update_time, question_id)
VALUES (14, '5', false, sysdate, sysdate, 4);
INSERT INTO choice (choice_id, choice_name, choice_correct, create_time, update_time, question_id)
VALUES (15, '-5', false, sysdate, sysdate, 4);
INSERT INTO choice (choice_id, choice_name, choice_correct, create_time, update_time, question_id)
VALUES (16, '10', false, sysdate, sysdate, 4);

-- question
INSERT INTO question (question_id, question_name, question_type, question_time, create_time, update_time, quiz_id)
VALUES (5, '18-?=10', 'S', 15, sysdate, sysdate, 1);

INSERT INTO choice (choice_id, choice_name, choice_correct, create_time, update_time, question_id)
VALUES (17, '8', true, sysdate, sysdate, 5);
INSERT INTO choice (choice_id, choice_name, choice_correct, create_time, update_time, question_id)
VALUES (18, '10', false, sysdate, sysdate, 5);
INSERT INTO choice (choice_id, choice_name, choice_correct, create_time, update_time, question_id)
VALUES (19, '18', false, sysdate, sysdate, 5);
INSERT INTO choice (choice_id, choice_name, choice_correct, create_time, update_time, question_id)
VALUES (20, '12', false, sysdate, sysdate, 5);

-- task
--INSERT INTO task (task_id, task_status, task_score, task_start, task_finish, quiz_user_id, quiz_id)
--VALUES ('', '', '', '', '', '', '');



