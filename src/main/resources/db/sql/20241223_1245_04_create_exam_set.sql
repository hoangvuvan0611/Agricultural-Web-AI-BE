DROP TABLE IF EXISTS tbl_exam_set;
CREATE TABLE tbl_exam_set (
                          id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                          subject_id BIGINT NULL DEFAULT NULL,
                          created_by VARCHAR(255) NOT NULL,
                          create_date DATETIME NOT NULL,
                          modified_by VARCHAR(255) NULL DEFAULT NULL,
                          modify_date DATETIME NULL DEFAULT NULL,
                          code VARCHAR(255) NULL DEFAULT NULL,
                          title VARCHAR(255) NULL DEFAULT NULL,
                          exam_number INT NULL DEFAULT NULL,
                          status INT NULL DEFAULT NULL,
                          FOREIGN KEY (subject_id) REFERENCES tbl_subject(id)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

ALTER TABLE tbl_exam
    ADD COLUMN exam_set_id BIGINT;


