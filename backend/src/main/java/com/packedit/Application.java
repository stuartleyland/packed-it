package com.packedit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
@Configuration
@ComponentScan({ "com.packedit", "com.auth0" })
@ImportResource("classpath:auth0-security-context.xml")
@PropertySource("classpath:auth0.properties")
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

}