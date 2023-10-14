package com.devweb.prontuario.repositories;


import com.devweb.prontuario.BaseTestContainers;
import com.devweb.prontuario.entities.Atestado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class AtestadoRepositoryTest extends BaseTestContainers {

    private AtestadoRepository underTest;


    @BeforeEach
    void setUp() {
        this.underTest = new AtestadoRepository (
                BaseTestContainers.getJbdcTemplate ( )
        );
    }

    @Test
    void findAllShouldNotReturnEmpty() {
        //Given
        Pageable pageable = PageRequest.of ( 1, 1 );

        //When
        Page<Atestado> page = this.underTest.findAll ( pageable );

        //Then
        assertThat ( page.getContent ( ) )
                .isNotEmpty ( );
    }

    @Test
    void EntityShouldBePresentWhenIdRight() {
        // Given
        String expectedId = "atestado_id";

        // When
        Optional<Atestado> result = this.underTest.findById ( expectedId );

        // Then
        assertThat ( result )
                .isPresent ( )
                .hasValueSatisfying ( a -> assertThat ( a.getId ( ) ).isEqualTo ( expectedId ) );

    }

    @Test
    void EntityShouldNotBePresentWhenIdWrong() {
        // Given
        String wrongId = "id";
        // When
        Optional<Atestado> result = this.underTest.findById ( wrongId );
        // Then
        assertThat ( result ).isNotPresent ( );
    }

    @Test
    void EntityShouldNotBePresentWhenDeleted() {
        // Given
        Pageable pageable = PageRequest.of ( 1, 10 );
        String expectedId = "atestado_id";

        // When
        this.underTest.delete ( expectedId );
        Optional<Atestado> result = this.underTest.findById ( expectedId );

        // Then
        assertThat ( result ).isNotPresent ( );
    }
}