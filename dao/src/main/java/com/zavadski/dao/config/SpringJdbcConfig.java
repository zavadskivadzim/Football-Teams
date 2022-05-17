package com.zavadski.dao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@PropertySource({"classpath:application.properties"})
public class SpringJdbcConfig {

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Profile("postgresql")
    public DataSource dataSourcePostgresql() {
        return new DriverManagerDataSource(
                "jdbc:postgresql://localhost:5432/football_teams"
                , System.getProperty("db_user")
                , System.getProperty("db_pass"));
    }

    @Profile("h2")
    public DataSource dataSourceH2() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScripts("schema.sql", "data.sql")
                .build();
    }

    @Bean
    public DataSource dataSource() {
        if (Objects.equals(activeProfile, "h2")) {
            return dataSourceH2();
        } else {
            return dataSourcePostgresql();
        }
    }
}
