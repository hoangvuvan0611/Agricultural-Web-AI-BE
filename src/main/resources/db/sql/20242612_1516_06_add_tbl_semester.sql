DROP TABLE IF EXISTS tbl_semester;
CREATE TABLE tbl_semester (
                                  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                  created_by VARCHAR(255) NOT NULL,
                                  create_date DATETIME NOT NULL,
                                  modified_by VARCHAR(255) NULL DEFAULT NULL,
                                  modify_date DATETIME NULL DEFAULT NULL,
                                  code VARCHAR(255) NULL DEFAULT NULL,
                                  title VARCHAR(255) NULL DEFAULT NULL,
                                  begin_time DATETIME NULL DEFAULT NULL,
                                  finish_time DATETIME NULL DEFAULT NULL
) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

