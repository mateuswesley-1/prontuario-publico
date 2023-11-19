//package com.devweb.prontuario.repositories.jbdc;
//
//import com.devweb.prontuario.BaseTestContainers;
//import com.devweb.prontuario.entities.Prescricao;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//class PrescricaoRepositoryTest extends BaseTestContainers {
//    private PrescricaoRepositoryJbdc underTest;
//
//    @BeforeEach
//    void setUp() {
//        this.underTest = new PrescricaoRepositoryJbdc (
//                this.getJbdcTemplate ( )
//        );
//    }
//
//    @Test
//    void findAllShouldNotReturnEmpty() {
//        //Given
//        Pageable pageable = PageRequest.of ( 1, 1 );
//
//        //When
//        Page<Prescricao> page = this.underTest.findAll ( pageable );
//
//        //Then
//        assertThat ( page.getContent ( ) )
//                .isNotEmpty ( );
//    }
//
//    @Test
//    void EntityShouldBePresentWhenIdRight() {
//        // Given
//        String expectedId = "prescricao_id";
//
//        // When
//        Optional<Prescricao> result = this.underTest.findById ( expectedId );
//
//        // Then
//        assertThat ( result )
//                .isPresent ( )
//                .hasValueSatisfying ( a -> assertThat ( a.getId ( ) ).isEqualTo ( expectedId ) );
//
//    }
//
//    @Test
//    void EntityShouldNotBePresentWhenIdWrong() {
//        // Given
//        String wrongId = "id";
//        // When
//        Optional<Prescricao> result = this.underTest.findById ( wrongId );
//        // Then
//        assertThat ( result ).isNotPresent ( );
//    }
//
//    @Test
//    void EntityShouldNotBePresentWhenDeleted() {
//        // Given
//        String expectedId = "prescricao_id";
//
//        // When
//        this.underTest.delete ( expectedId );
//        Optional<Prescricao> result = this.underTest.findById ( expectedId );
//
//        // Then
//        assertThat ( result ).isNotPresent ( );
//    }
//}