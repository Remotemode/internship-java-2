package com.remotemode.internshipjava2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class InternshipJava2Application {

    public static void main(String[] args) {
        SpringApplication.run(InternshipJava2Application.class, args);
    }

}
