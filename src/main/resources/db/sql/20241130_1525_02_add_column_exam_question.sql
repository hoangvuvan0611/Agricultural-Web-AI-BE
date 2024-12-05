ALTER TABLE tbl_exam_question
    ADD COLUMN created_by VARCHAR(255);
ALTER TABLE tbl_exam_question
    ADD COLUMN create_date DATETIME;
ALTER TABLE tbl_exam_question
    ADD COLUMN modified_by VARCHAR(255);
ALTER TABLE tbl_exam_question
    ADD COLUMN modify_date DATETIME;