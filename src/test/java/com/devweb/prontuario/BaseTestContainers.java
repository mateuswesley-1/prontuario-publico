package com.devweb.prontuario;

import com.github.javafaker.Faker;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;

@Testcontainers
public class BaseTestContainers {

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
