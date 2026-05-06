package com.healthify.opdservice.config.dataBase;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfig {

    @Bean(initMethod = "migrate")
    public Flyway opdFlyway(@Qualifier("opdDataSource")HikariDataSource opdDataSource){
        Flyway opdFlyway =  Flyway.configure().dataSource(opdDataSource)
                .baselineOnMigrate(true)
                .locations("classpath:db/migration/opdService")
                .load();
        return opdFlyway;
    }

    @Bean(initMethod = "migrate")
    public Flyway authFlyway(@Qualifier("userDataSource") HikariDataSource userDataSource){
        return Flyway.configure()
                .dataSource(userDataSource)
                .baselineOnMigrate(true)
                .locations("classpath:db/migration/auth")
                .load();
    }


}
