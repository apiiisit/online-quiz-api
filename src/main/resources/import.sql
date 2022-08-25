-- user_role
INSERT INTO user_role (user_role_id, user_role_name, user_role_description)
VALUES (1, 'A', 'admin');

INSERT INTO user_role (user_role_id, user_role_name, user_role_description)
VALUES (2, 'U', 'user');

-- quiz_user
INSERT INTO quiz_user (user_id, user_name, first_name, last_name, tel, email, password, profile, create_time, update_time, user_role_id)
VALUES (1, 'admin', 'Apisit', 'Noiluangchai', '0623439913', 'apisitn9244@gmail.com', '207291B1419A721D4D18B23CFBF40EBB', null, sysdate, sysdate, 1);
--password san
INSERT INTO quiz_user (user_id, user_name, first_name, last_name, tel, email, password, profile, create_time, update_time, user_role_id)
VALUES (2, 'tong', 'Wipawee', 'Phattanarach', '0800546884', 'wipawee@outlook.com', '1B29518EE0E136EF49D2A6A54528BC44', 'tong.jpg', sysdate, sysdate, 2);
--password user
INSERT INTO quiz_user (user_id, user_name, first_name, last_name, tel, email, password, profile, create_time, update_time, user_role_id)
VALUES (3, 'san', 'อภิสิทธิ์', 'น้อยหลวงชัย', '0623439913', 'noiluangchai.apisit@gmail.com', 'EDAE5DE839DD806ED5D2FD7D45E3B0DC', 'san.jpg', sysdate, sysdate, 2);
--password 143418

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

-- choice_correct
INSERT INTO choice_correct (choice_correct_id, choice_correct_check)
VALUES (0, false);
INSERT INTO choice_correct (choice_correct_id, choice_correct_check)
VALUES (1, true);

-- question
INSERT INTO question (question_id, question_name, question_type, question_time, verified, choice_correct_length, create_time, update_time, quiz_id)
VALUES (1, '1+1=?', 'S', 15, true, 1, sysdate, sysdate, 1);

INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (1, '1', 0, sysdate, sysdate, 1);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (2, '2', 1, sysdate, sysdate, 1);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (3, '3', 0, sysdate, sysdate, 1);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (4, '4', 0, sysdate, sysdate, 1);

-- question
INSERT INTO question (question_id, question_name, question_type, question_time, verified, choice_correct_length, create_time, update_time, quiz_id)
VALUES (2, '? + ? = 69', 'M', 15, true, 2, sysdate, sysdate, 1);

INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (5, '34+35', 1, sysdate, sysdate, 2);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (6, '33+34', 0, sysdate, sysdate, 2);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (7, '68+1', 1, sysdate, sysdate, 2);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (8, '69+69', 0, sysdate, sysdate, 2);

-- question
INSERT INTO question (question_id, question_name, question_type, question_time, verified, choice_correct_length, create_time, update_time, quiz_id)
VALUES (3, '1 + ? = 2', 'M', 15, true, 2, sysdate, sysdate, 1);

INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (9, '1', 1, sysdate, sysdate, 3);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (10, '1/1', 1, sysdate, sysdate, 3);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (11, '3', 0, sysdate, sysdate, 3);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (12, '4', 0, sysdate, sysdate, 3);

-- question
INSERT INTO question (question_id, question_name, question_type, question_time, verified, choice_correct_length, create_time, update_time, quiz_id)
VALUES (4, '9 + 10 = ?', 'S', 15, true, 1, sysdate, sysdate, 1);

INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (13, '17', 0, sysdate, sysdate, 4);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (14, '18', 0, sysdate, sysdate, 4);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (15, '19', 1, sysdate, sysdate, 4);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (16, '20', 0, sysdate, sysdate, 4);

-- question
INSERT INTO question (question_id, question_name, question_type, question_time, verified, choice_correct_length, create_time, update_time, quiz_id)
VALUES (5, 'x + y = 100', 'S', 15, true, 1, sysdate, sysdate, 1);

INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (17, 'x = 50, y = 50', 1, sysdate, sysdate, 5);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (18, 'x = 45, y = 50', 0, sysdate, sysdate, 5);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (19, 'x = 50, y = 51', 0, sysdate, sysdate, 5);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (20, 'x = 100, y = 50', 0, sysdate, sysdate, 5);

-- task
INSERT INTO task (task_id, task_status, task_score, task_pass, task_start, task_finish, user_id, quiz_id)
VALUES (1, true, 5, 50, sysdate, sysdate, 1, 1);

INSERT INTO task (task_id, task_status, task_score, task_pass, task_start, task_finish, user_id, quiz_id)
VALUES (2, true, 5, 50, sysdate, sysdate, 1, 2);

INSERT INTO task (task_id, task_status, task_score, task_pass, task_start, task_finish, user_id, quiz_id)
VALUES (3, false, 5, 50, sysdate, sysdate, 2, 1);

INSERT INTO task (task_id, task_status, task_score, task_pass, task_start, task_finish, user_id, quiz_id)
VALUES (4, false, 0, 50, sysdate, sysdate, 2, 2);

INSERT INTO task (task_id, task_status, task_score, task_pass, task_start, task_finish, user_id, quiz_id)
VALUES (5, false, 5, 50, sysdate, sysdate, 3, 1);

INSERT INTO task (task_id, task_status, task_score, task_pass, task_start, task_finish, user_id, quiz_id)
VALUES (6, true, 0, 50, sysdate, sysdate, 3, 2);

INSERT INTO counter (counter_id, counter_category, counter_quiz, counter_question, counter_user, counter_task)
VALUES (1, 2, 2, 5, 3, 6);

