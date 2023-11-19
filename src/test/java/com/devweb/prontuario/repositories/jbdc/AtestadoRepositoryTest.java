//package com.devweb.prontuario.repositories.jbdc;
//
//
//import com.devweb.prontuario.BaseTestContainers;
//import com.devweb.prontuario.entities.Atestado;
//import org.jetbrains.annotations.NotNull;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.MethodSource;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//import java.util.UUID;
//import java.util.stream.Stream;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//class AtestadoRepositoryTest extends BaseTestContainers {
//
//    private AtestadoRepositoryJbdc underTest;
//
//    @BeforeEach
//    void setUp() {
//        this.underTest = new AtestadoRepositoryJbdc (
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
//        Page<Atestado> page = this.underTest.findAll ( pageable );
//
//        //Then
//        assertThat ( page.getContent ( ) )
//                .isNotEmpty ( );
//    }
//
//    @Test
//    void EntityShouldBePresentWhenIdRight() {
//        // Given
//        String expectedId = "atestadoId";
//
//        // When
//        Optional<Atestado> result = this.underTest.findById ( expectedId );
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
//        Optional<Atestado> result = this.underTest.findById ( wrongId );
//        // Then
//        assertThat ( result ).isNotPresent ( );
//    }
//
//    @Test
//    void EntityShouldNotBePresentWhenDeleted() {
//        // Given
//        String expectedId = "atestadoId";
//
//        // When
//        this.underTest.delete ( expectedId );
//        Optional<Atestado> result = this.underTest.findById ( expectedId );
//
//        // Then
//        assertThat ( result ).isNotPresent ( );
//    }
//
//    @ParameterizedTest
//    @MethodSource("durationsValidas")
//    void ShouldBePossibleCreateAtestadoWhenDurationInRange(int duracao){
//        // Given
//        Atestado expected = getAtestado( duracao, "descricao da doenca" );
//
//        // When
//        Atestado created = this.underTest.save ( expected );
//
//        // Then
//        assertThat ( Optional.of ( created ) ).isPresent ();
//    }
//    @ParameterizedTest
//    @MethodSource("durationsInvalidas")
//    void ShouldNotBePossibleCreateAtestadoWhenDurationOutOfRange(int duracao){
//        // Given
//        Atestado expected = getAtestado(duracao, "descricao da doenca");
//
//        // When e Then
//        assertThrows (
//                DataIntegrityViolationException.class,
//                () ->  this.underTest.save ( expected )
//        );
//    }
//
//    @ParameterizedTest
//    @MethodSource("descricoesValidas")
//    void ShouldBePossibleCreateAtestadoWhenDescricaoLengthInRange(String descricao){
//        // Given
//        Atestado expected = getAtestado(5, descricao);
//
//
//        // When
//        Atestado created = this.underTest.save ( expected );
//
//        // Then
//        assertThat ( Optional.of ( created ) ).isPresent ();
//    }
//    @ParameterizedTest
//    @MethodSource("descricoesInvalidas")
//    void ShouldNotBePossibleCreateAtestadoWhenDescricaoLengthOutOfRange(String descricao){
//        // Given
//        Atestado expected = getAtestado(5, descricao);
//
//        // When e Then
//        assertThrows (
//                DataIntegrityViolationException.class,
//                () ->  this.underTest.save ( expected )
//        );
//    }
//
//
//    static Stream<Integer> durationsInvalidas() {
//        return Stream.of(-1, 0, 31, 40);
//    }
//
//    static Stream<String> descricoesInvalidas() {
//        return Stream.of(
//                faker.lorem ().characters ( 251 ),
//                faker.lorem ().characters ( 300 )
//        );
//    }
//
//    static Stream<Integer> durationsValidas() {
//        return Stream.of(1, 2, 5, 30, 29);
//    }
//
//    static Stream<String> descricoesValidas() {
//        return Stream.of(
//                faker.lorem ().characters ( 250 ),
//                faker.lorem ().characters ( 249 ),
//                faker.lorem ().characters ( 1 ),
//                faker.lorem ().characters ( 2 ),
//                faker.lorem ().characters ( 10 ),
//                faker.lorem ().characters ( 0 )
//        );
//    }
//
//    @NotNull
//    private static Atestado getAtestado(int duracao, String descricao) {
//        Atestado expected = new Atestado ();
//        expected.setDuracao ( duracao );
//        expected.setDescricao ( descricao );
//        expected.setCreatedAt ( LocalDateTime.now());
//        expected.setUpdatedAt (LocalDateTime.now());
//        expected.setId( UUID.randomUUID().toString());
//        return expected;
//    }
//}