package com.devweb.prontuario.repositories.jbdc;

import com.devweb.prontuario.BaseTestContainers;
import com.devweb.prontuario.entities.Medicamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MedicamentoRepositoryTest extends BaseTestContainers{
    private MedicamentoRepositoryJbdc underTest;

    @BeforeEach
    void setUp() {
        this.underTest = new MedicamentoRepositoryJbdc (
                this.getJbdcTemplate ( )
        );
    }

    @Test
    void findAllShouldNotReturnEmpty() {
        //Given
        Pageable pageable = PageRequest.of ( 1, 1 );

        //When
        Page<Medicamento> page = this.underTest.findAll ( pageable );

        //Then
        assertThat ( page.getContent ( ) )
                .isNotEmpty ( );
    }

    @Test
    void EntityShouldBePresentWhenIdRight() {
        // Given
        String expectedId = "medicamentoId";

        // When
        Optional<Medicamento> result = this.underTest.findById ( expectedId );

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
        Optional<Medicamento> result = this.underTest.findById ( wrongId );
        // Then
        assertThat ( result ).isNotPresent ( );
    }

    @Test
    void EntityShouldNotBePresentWhenDeleted() {
        // Given
        String expectedId = "medicamentoId";

        // When
        this.underTest.delete ( expectedId );
        Optional<Medicamento> result = this.underTest.findById ( expectedId );

        // Then
        assertThat ( result ).isNotPresent ( );
    }
}