//package com.devweb.prontuario.repositories.jbdc;
//
//import com.devweb.prontuario.BaseTestContainers;
//import com.devweb.prontuario.entities.Consulta;
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
//public class ConsultaRepositoryTest extends BaseTestContainers {
//    private ConsultaRepositoryJbdc underTest;
//
//    @BeforeEach
//    void setUp() {
//        this.underTest = new ConsultaRepositoryJbdc (
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
//        Page<Consulta> page = this.underTest.findAll ( pageable );
//
//        //Then
//        assertThat ( page.getContent ( ) )
//                .isNotEmpty ( );
//    }
//
//    @Test
//    void EntityShouldBePresentWhenIdRight() {
//        // Given
//        String expectedId = "consultaId";
//
//        // When
//        Optional<Consulta> result = this.underTest.findById ( expectedId );
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
//        Optional<Consulta> result = this.underTest.findById ( wrongId );
//        // Then
//        assertThat ( result ).isNotPresent ( );
//    }
//
//    @Test
//    void EntityShouldNotBePresentWhenDeleted() {
//
//        // Given
//        String expectedId = "consultaId";
//
//        // When
//        this.underTest.delete ( expectedId );
//        Optional<Consulta> result = this.underTest.findById ( expectedId );
//
//        // Then
//        assertThat ( result ).isNotPresent ( );
//    }
//}
