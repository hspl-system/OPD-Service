package com.healthify.opdservice;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class OpdserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpdserviceApplication.class, args);

	}

//    @Bean
//    public Flyway flyway(DataSource dataSource) {
//        Flyway flyway = Flyway.configure()
//               .dataSource(dataSource)
//               .load();
//       flyway.migrate();
//       return flyway;
//    }



}
