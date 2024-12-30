ALTER TABLE tbl_exam_session_student
ADD COLUMN created_by VARCHAR(255);

ALTER TABLE tbl_exam_session_student
ADD COLUMN create_date DATETIME;

ALTER TABLE tbl_exam_session_student
ADD COLUMN modified_by VARCHAR(255);

ALTER TABLE tbl_exam_session_student
ADD COLUMN modify_date DATETIME;

