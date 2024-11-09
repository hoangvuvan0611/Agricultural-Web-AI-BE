-- Tạo database nếu chưa tồn tại
CREATE DATABASE IF NOT EXISTS exam_management;

-- Sử dụng database vừa tạo
USE exam_management;

DROP TABLE IF EXISTS tbl_department;
CREATE TABLE tbl_department (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255),
    code VARCHAR(255),
    description VARCHAR(255)
);

DROP TABLE IF EXISTS tbl_subject;
CREATE TABLE tbl_subject (
    id VARCHAR(255) PRIMARY KEY,
		department_id VARCHAR(255),
    name VARCHAR(255),
    code VARCHAR(255),
    description VARCHAR(255),
	FOREIGN KEY (department_id) REFERENCES tbl_department(id)
);

DROP TABLE IF EXISTS tbl_chapter;
CREATE TABLE tbl_chapter (
    id VARCHAR(255) PRIMARY KEY,
    subject_id VARCHAR(255),
    name VARCHAR(255),
    description VARCHAR(255),
    FOREIGN KEY (subject_id) REFERENCES tbl_subject(id)
);

DROP TABLE IF EXISTS tbl_question;
CREATE TABLE tbl_question (
    id VARCHAR(255) PRIMARY KEY,
    chapter_id VARCHAR(255),
    create_date DATETIME,
    created_by VARCHAR(255),
    modify_date DATETIME,
    modified_by VARCHAR(255),
    content TEXT,
    image TEXT,
    type VARCHAR(50),
    FOREIGN KEY (chapter_id) REFERENCES tbl_chapter(id)
);

DROP TABLE IF EXISTS tbl_answer;
CREATE TABLE tbl_answer (
    id VARCHAR(255) PRIMARY KEY,
    question_id VARCHAR(255),
    content VARCHAR(255),
    is_correct BIT(1),
    create_date DATETIME,
    created_by VARCHAR(255),
    modify_date DATETIME,
    modified_by VARCHAR(255),
    FOREIGN KEY (question_id) REFERENCES tbl_question(id)
);


DROP TABLE IF EXISTS tbl_exam;
CREATE TABLE tbl_exam (
    id VARCHAR(255) PRIMARY KEY,
    subject_id VARCHAR(255),
    created_by VARCHAR(255),
    title VARCHAR(255),
    description VARCHAR(255),
    duration INT,
    total_questions INT,
    is_active BIT(1),
    create_date DATETIME,
    modified_by VARCHAR(255),
    modify_date DATETIME,
    exam_date DATETIME,
    FOREIGN KEY (subject_id) REFERENCES tbl_subject(id)
);


DROP TABLE IF EXISTS tbl_exam_question;
CREATE TABLE tbl_exam_question (
    id VARCHAR(255) PRIMARY KEY,
    exam_id VARCHAR(255),
    question_id VARCHAR(255),
    score DOUBLE,
    order_number INT,
    FOREIGN KEY (exam_id) REFERENCES tbl_exam(id),
    FOREIGN KEY (question_id) REFERENCES tbl_question(id)
);


DROP TABLE IF EXISTS tbl_user;
CREATE TABLE tbl_user (
    id VARCHAR(255) PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255),
    code VARCHAR(50),
    full_name VARCHAR(255),
    role INT,
    created_by VARCHAR(255),
    create_date DATETIME,
    modified_by VARCHAR(255),
    modify_date DATETIME,
    is_active BIT(1)
);


DROP TABLE IF EXISTS tbl_exam_session;
CREATE TABLE tbl_exam_session (
    id VARCHAR(255) PRIMARY KEY,
    exam_id VARCHAR(255),
    student_id VARCHAR(255),
	teacher_id VARCHAR(255),
    start_time DATETIME,
    end_time DATETIME,
    submit_time DATETIME,
    status INT,
    FOREIGN KEY (exam_id) REFERENCES tbl_exam(id),
    FOREIGN KEY (student_id) REFERENCES tbl_user(id),
	FOREIGN KEY (teacher_id) REFERENCES tbl_user(id)
);


DROP TABLE IF EXISTS tbl_student_answers;
CREATE TABLE tbl_student_answers (
    id VARCHAR(255) PRIMARY KEY,
    session_id VARCHAR(255),
    question_id VARCHAR(255),
    answer_id VARCHAR(255),
    answer_date DATETIME,
    FOREIGN KEY (session_id) REFERENCES tbl_exam_session(id),
    FOREIGN KEY (question_id) REFERENCES tbl_exam_question(question_id),
    FOREIGN KEY (answer_id) REFERENCES tbl_answer(id)
);

DROP TABLE IF EXISTS tbl_exam_result;
CREATE TABLE tbl_exam_result (
    id VARCHAR(255) PRIMARY KEY,
    session_id VARCHAR(255),
    total_score DOUBLE,
    correct_count INT,
    wrong_count INT,
    un_answer_count INT,
    FOREIGN KEY (session_id) REFERENCES tbl_exam_session(id)
);

DROP TABLE IF EXISTS file_description;
CREATE TABLE file_description (
    id VARCHAR(255) PRIMARY KEY,
    create_date DATETIME,
    created_by VARCHAR(255),
    modify_date DATETIME,
    modified_by VARCHAR(255),
    content_size INT,
    content_type VARCHAR(50),
    name VARCHAR(255)
);