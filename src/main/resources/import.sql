-- user_role
INSERT INTO user_role (user_role_id, user_role_name, user_role_description)
VALUES (1, 'A', 'admin');

INSERT INTO user_role (user_role_id, user_role_name, user_role_description)
VALUES (2, 'U', 'user');

-- quiz_user
INSERT INTO quiz_user (user_id, user_name, first_name, last_name, tel, email, password, profile, create_time, update_time, user_role_id)
VALUES (1, 'admin', 'Apisit', 'Noiluangchai', '0623439913', 'apisitn9244@gmail.com', 'DACCB74B3B6102F75598B517662DD85F', null, sysdate, sysdate, 1);
--password admin123
INSERT INTO quiz_user (user_id, user_name, first_name, last_name, tel, email, password, profile, create_time, update_time, user_role_id)
VALUES (2, 'tong', 'Wipawee', 'Phatyai', '0800546884', 'wipawee@outlook.com', '1B29518EE0E136EF49D2A6A54528BC44', 'tong.png', sysdate, sysdate, 2);
--password user
INSERT INTO quiz_user (user_id, user_name, first_name, last_name, tel, email, password, profile, create_time, update_time, user_role_id)
VALUES (3, 'san', 'อภิสิทธิ์', 'น้อยหลวงชัย', '0623439913', 'apiiisit@outlook.com', '8E57277EF728438139967180DB2EAF1D', 'san.jpg', sysdate, sysdate, 2);
--password usersan

-- category
INSERT INTO category (category_id, category_name, category_description, category_active, create_time, update_time)
VALUES (1, 'Basic math', 'คณิตศาสตร์พื้นฐาน', true, sysdate, sysdate);

INSERT INTO category (category_id, category_name, category_description, category_active, create_time, update_time)
VALUES (2, 'Mobile Application Development', 'การพัฒนาแอพพลิเคชั่นบนมือถือ', true, sysdate, sysdate);

-- quiz
INSERT INTO quiz (quiz_id, quiz_name, quiz_description, quiz_pass, number_of_question, quiz_start, quiz_end, average_test_time, quiz_active, quiz_password, create_time, update_time, category_id)
VALUES (1, 'Math 1', null, 50, 5, sysdate, sysdate, 5, true, 123456, sysdate, sysdate, 1);

INSERT INTO quiz (quiz_id, quiz_name, quiz_description, quiz_pass, number_of_question, quiz_start, quiz_end, average_test_time, quiz_active, quiz_password, create_time, update_time, category_id)
VALUES (2, 'Mobile 1', null, 50, 5, sysdate, sysdate, 3, true, 123456, sysdate, sysdate, 2);

-- choice_correct
INSERT INTO choice_correct (choice_correct_id, choice_correct_check)
VALUES (0, false);
INSERT INTO choice_correct (choice_correct_id, choice_correct_check)
VALUES (1, true);

-- question
INSERT INTO question (question_id, question_name, question_type, question_time, verified, choice_correct_length, create_time, update_time, quiz_id)
VALUES (1, '1+1=?', 'S', 60, true, 1, sysdate, sysdate, 1);

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
VALUES (2, '? + ? = 69', 'M', 60, true, 2, sysdate, sysdate, 1);

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
VALUES (3, '1 + ? = 2', 'M', 60, true, 2, sysdate, sysdate, 1);

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
VALUES (4, '9 + 10 = ?', 'S', 60, true, 1, sysdate, sysdate, 1);

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
VALUES (5, 'x + y = 100', 'S', 60, true, 1, sysdate, sysdate, 1);

INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (17, 'x = 50, y = 50', 1, sysdate, sysdate, 5);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (18, 'x = 45, y = 50', 0, sysdate, sysdate, 5);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (19, 'x = 50, y = 51', 0, sysdate, sysdate, 5);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (20, 'x = 100, y = 50', 0, sysdate, sysdate, 5);

-- question
INSERT INTO question (question_id, question_name, question_type, question_time, verified, choice_correct_length, create_time, update_time, quiz_id)
VALUES (6, 'Application (แอปพลิเคชัน) หมายถึงอะไร', 'S', 60, true, 1, sysdate, sysdate, 2);

INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (21, 'โปรแกรมที่รันบนระบบปฏิบัติการ Framework (เฟรมเวิร์ก)', 0, sysdate, sysdate, 6);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (22, 'โปรแกรมที่อำนวยความสะดวกที่ออกแบบมาสำหรับ Mobile', 1, sysdate, sysdate, 6);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (23, 'โปรแกรมที่เขียนขึ้นมาเพื่อเป็น Browser (บราวเซอร์)', 0, sysdate, sysdate, 6);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (24, 'โปรแกรมที่ถูกพัฒนาด้วย Library (ไลบรารี่)', 0, sysdate, sysdate, 6);

-- question
INSERT INTO question (question_id, question_name, question_type, question_time, verified, choice_correct_length, create_time, update_time, quiz_id)
VALUES (7, 'ข้อใดไม่ใช่ขั้นตอนการพัฒนาแอปพลิเคชัน', 'S', 60, true, 1, sysdate, sysdate, 2);

INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (25, 'การศึกษาความต้องการ', 0, sysdate, sysdate, 7);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (26, 'การออกแบบ', 0, sysdate, sysdate, 7);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (27, 'การสร้างแอปพลิเคชัน', 0, sysdate, sysdate, 7);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (28, 'การจำหน่ายแอป', 1, sysdate, sysdate, 7);

-- question
INSERT INTO question (question_id, question_name, question_type, question_time, verified, choice_correct_length, create_time, update_time, quiz_id)
VALUES (8, 'ขั้นตอนใดของการพัฒนาแอปพลิเคชันที่สำคัญที่สุด', 'S', 60, true, 1, sysdate, sysdate, 2);

INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (29, 'การศึกษาความต้องการ', 0, sysdate, sysdate, 8);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (30, 'การออกแบบ', 0, sysdate, sysdate, 8);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (31, 'การสร้างแอปพลิเคชัน และทดสอบ', 0, sysdate, sysdate, 8);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (32, 'สำคัญทุกขั้นตอน', 1, sysdate, sysdate, 8);

-- question
INSERT INTO question (question_id, question_name, question_type, question_time, verified, choice_correct_length, create_time, update_time, quiz_id)
VALUES (9, 'การใช้งานการ์ดคัมบัง สำหรับแอปพลิเคชันที่ไม่ซับซ้อนมาก จะต้องแบ่งเฟสการทำงาน ข้อใดไม่อยู่ในเฟสการทำงาน', 'S', 60, true, 1, sysdate, sysdate, 2);

INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (33, 'Todo เฟสที่จะทำ', 0, sysdate, sysdate, 9);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (34, 'Doing เฟสที่กำลังทำ', 0, sysdate, sysdate, 9);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (35, 'Done เฟสที่ทำเสร็จแล้ว', 0, sysdate, sysdate, 9);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (36, 'Test เฟสที่ทดสอบระบบ', 1, sysdate, sysdate, 9);

-- question
INSERT INTO question (question_id, question_name, question_type, question_time, verified, choice_correct_length, create_time, update_time, quiz_id)
VALUES (10, 'ข้อใด ไม่ใช่ ระบบปฏิบัติการที่ใช้ในโทรศัพท์เคลื่อนที่', 'S', 60, true, 1, sysdate, sysdate, 2);

INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (37, 'iOS', 0, sysdate, sysdate, 10);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (38, 'android', 0, sysdate, sysdate, 10);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (39, 'linux', 1, sysdate, sysdate, 10);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (40, 'windows Phone', 0, sysdate, sysdate, 10);

-- question
INSERT INTO question (question_id, question_name, question_type, question_time, verified, choice_correct_length, create_time, update_time, quiz_id)
VALUES (11, 'เครื่องมือที่นิยมใช้ในการวางแผนการทำงานพัฒนาแอปพลิเคชัน คือ เครื่องมือข้อใด', 'S', 60, true, 1, sysdate, sysdate, 2);

INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (41, 'เทคนิคกูเกิล คีม', 0, sysdate, sysdate, 11);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (42, 'เทคนิคกระดานดำ', 0, sysdate, sysdate, 11);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (43, 'เทคนิคกระดานคัมบัง', 1, sysdate, sysdate, 11);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (44, 'เทคนิคไดร์ฟคลาวด์', 0, sysdate, sysdate, 11);

-- question
INSERT INTO question (question_id, question_name, question_type, question_time, verified, choice_correct_length, create_time, update_time, quiz_id)
VALUES (12, 'กระบวนการพัฒนาแอปพลิเคชันสมัยใหม่ ใช้แนวคิดใดในการพัฒนา', 'S', 60, true, 1, sysdate, sysdate, 2);

INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (45, 'แนวคิดการพัฒนาซอฟตร์แวร์แบบเอาท์พุท', 0, sysdate, sysdate, 12);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (46, 'แนวคิดการพัฒนาซอฟต์แวร์แบบเอจายล์', 1, sysdate, sysdate, 12);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (47, 'แนวคิดแบบโอดี', 0, sysdate, sysdate, 12);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (48, 'แนวคิดแบบอินคัม', 0, sysdate, sysdate, 12);

-- question
INSERT INTO question (question_id, question_name, question_type, question_time, verified, choice_correct_length, create_time, update_time, quiz_id)
VALUES (13, 'เครื่องมือที่นิยมนำมาใช้บริหารจัดการงานในรูปแบบคัมบัง คือข้อใด', 'S', 60, true, 1, sysdate, sysdate, 2);

INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (49, 'โปรแกรมกูเกิ้ลไดร์ฟ', 0, sysdate, sysdate, 13);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (50, 'โปรแกรมเทรลโล', 1, sysdate, sysdate, 13);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (51, 'โปรแกรมพาวเวอร์พอยต์', 0, sysdate, sysdate, 13);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (52, 'โปรแกรมคริปโต', 0, sysdate, sysdate, 13);

-- question
INSERT INTO question (question_id, question_name, question_type, question_time, verified, choice_correct_length, create_time, update_time, quiz_id)
VALUES (14, 'ปัญหาสำคัญในขั้นตอนการศึกษาความต้องการคือปัญหาในข้อใด', 'S', 60, true, 1, sysdate, sysdate, 2);

INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (53, 'การตรวจสอบคุณภาพของผลิตภัณฑ์ที่ไม่ได้มาตรฐาน', 0, sysdate, sysdate, 14);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (54, 'การสื่อสารที่คลาดเคลื่อนระหว่างผู้ใช้และผู้พัฒนาโดยเฉพาะอย่างยิ่งกรณีที่ผู้ใช้ขาดความเข้าใจเทคโนโลยี', 1, sysdate, sysdate, 14);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (55, 'การออกแบบแอปพลิเคชันที่มีการใช้งานที่ซับซ้อน', 0, sysdate, sysdate, 14);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (56, 'การจำหน่ายแอปพลิเคชันที่ราคาแพง', 0, sysdate, sysdate, 14);

-- question
INSERT INTO question (question_id, question_name, question_type, question_time, verified, choice_correct_length, create_time, update_time, quiz_id)
VALUES (15, 'ขั้นใดที่เป็นการหาข้อผิดพลาดและการปรับปรุงของการพัฒนาแอปพลิเคชัน', 'S', 60, true, 1, sysdate, sysdate, 2);

INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (57, 'การทดสอบ', 1, sysdate, sysdate, 15);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (58, 'การออกแบบ', 0, sysdate, sysdate, 15);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (59, 'การสร้างแอปพลิเคชัน', 0, sysdate, sysdate, 15);
INSERT INTO choice (choice_id, choice_name, choice_correct_id, create_time, update_time, question_id)
VALUES (60, 'การศึกษาความต้องการ', 0, sysdate, sysdate, 15);

-- task
INSERT INTO task (task_id, task_status, task_score, task_pass, task_start, task_finish, user_id, quiz_id)
VALUES (1, true, 5, '100%', sysdate, sysdate, 1, 1);

INSERT INTO task (task_id, task_status, task_score, task_pass, task_start, task_finish, user_id, quiz_id)
VALUES (2, true, 5, '100%', sysdate, sysdate, 1, 2);

INSERT INTO task (task_id, task_status, task_score, task_pass, task_start, task_finish, user_id, quiz_id)
VALUES (3, false, 2, '40%', sysdate, sysdate, 2, 1);

INSERT INTO task (task_id, task_status, task_score, task_pass, task_start, task_finish, user_id, quiz_id)
VALUES (4, false, 1, '20%', sysdate, sysdate, 2, 2);

INSERT INTO task (task_id, task_status, task_score, task_pass, task_start, task_finish, user_id, quiz_id)
VALUES (5, false, 2, '40%', sysdate, sysdate, 3, 1);

INSERT INTO task (task_id, task_status, task_score, task_pass, task_start, task_finish, user_id, quiz_id)
VALUES (6, true, 5, '100%', sysdate, sysdate, 3, 2);


INSERT INTO counter (counter_id, counter_category, counter_quiz, counter_question, counter_user, counter_task)
VALUES (1, 2, 2, 5, 3, 6);

