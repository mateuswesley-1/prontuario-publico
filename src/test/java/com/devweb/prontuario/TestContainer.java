package com.devweb.prontuario;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class TestContainer extends BaseTestContainers {

    @Test
    void canStartPostgresDB() {
        assertThat ( postgreSQLContainer.isCreated ( ) ).isTrue ( );
        assertThat ( postgreSQLContainer.isRunning ( ) ).isTrue ( );
    }


}
