package com.ttcn.vnuaexam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class VnuaExamApplication {

    public static void main(String[] args) {
        SpringApplication.run(VnuaExamApplication.class, args);
    }

}
