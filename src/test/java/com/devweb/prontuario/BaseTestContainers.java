package com.devweb.prontuario;

import com.github.javafaker.Faker;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
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

    @BeforeAll
    static void beforeAll() {
        Flyway flyway = Flyway.configure().dataSource(
                postgreSQLContainer.getJdbcUrl(),
                postgreSQLContainer.getUsername(),
                postgreSQLContainer.getPassword()
        )
                .locations("classpath:db/migration/test_migrations")
                .load();

        flyway.migrate();
        System.out.println (  );
    }

    @Container
    protected static final PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer ("postgres:latest")
                    .withDatabaseName ( "prontuario-repo-unit-test" )
                    .withUsername ( "mateus" )
                    .withPassword ( "senha" );
    @DynamicPropertySource
    private static void registerDataSourceProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    private static DataSource getDataSource(){
        DataSourceBuilder<?> builder = DataSourceBuilder.create()
                .driverClassName(postgreSQLContainer.getDriverClassName())
                .url(postgreSQLContainer.getJdbcUrl())
                .username ( postgreSQLContainer.getUsername () )
                .password ( postgreSQLContainer.getPassword () );
        return builder.build ();
    }

    public static NamedParameterJdbcTemplate getJbdcTemplate(){
        return new NamedParameterJdbcTemplate(BaseTestContainers.getDataSource ());
    }

    protected static final Faker faker = new Faker();

}
