package com.devweb.prontuario;

import com.github.javafaker.Faker;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;

@Testcontainers
public class BaseTestContainers {

    @BeforeEach
     void beforeAll() {
        Flyway flyway = Flyway.configure().dataSource(
                postgreSQLContainer.getJdbcUrl(),
                postgreSQLContainer.getUsername(),
                postgreSQLContainer.getPassword()
        )
                .locations("classpath:db/test_migration")
                .load();

        flyway.migrate();
    }

    @Container
    protected final PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer ("postgres:latest")
                    .withDatabaseName ( "prontuario-repo-unit-test" )
                    .withUsername ( "mateus" )
                    .withPassword ( "senha" );

    private DataSource getDataSource(){
        DataSourceBuilder<?> builder = DataSourceBuilder.create()
                .driverClassName(postgreSQLContainer.getDriverClassName())
                .url(postgreSQLContainer.getJdbcUrl())
                .username ( postgreSQLContainer.getUsername () )
                .password ( postgreSQLContainer.getPassword () );
        return builder.build ();
    }

    public NamedParameterJdbcTemplate getJbdcTemplate(){
        return new NamedParameterJdbcTemplate(this.getDataSource ());
    }

    protected static final Faker faker = new Faker();

}
