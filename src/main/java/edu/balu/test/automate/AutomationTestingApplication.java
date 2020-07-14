package edu.balu.test.automate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories
@SpringBootApplication
public class AutomationTestingApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutomationTestingApplication.class, args);
    }

}
