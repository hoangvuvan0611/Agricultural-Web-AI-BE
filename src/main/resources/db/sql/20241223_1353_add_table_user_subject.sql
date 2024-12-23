DROP TABLE IF EXISTS tbl_user_subject;
CREATE TABLE tbl_user_subject (
                                  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                  created_by VARCHAR(255) NOT NULL,
                                  create_date DATETIME NOT NULL,
                                  modified_by VARCHAR(255) NULL DEFAULT NULL,
                                  modify_date DATETIME NULL DEFAULT NULL,
                                  user_id BIGINT NOT NULL,
                                  subject_id BIGINT NOT NULL,
                                  FOREIGN KEY (user_id) REFERENCES tbl_user(id) ON DELETE CASCADE,
                                  FOREIGN KEY (subject_id) REFERENCES tbl_subject(id) ON DELETE CASCADE
) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

