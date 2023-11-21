//package com.devweb.prontuario.journey;
//
//
//import com.devweb.prontuario.BaseIntegrationTest;
//import com.devweb.prontuario.dto.atestado.AtestadoRequestDTO;
//import com.devweb.prontuario.entities.Atestado;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.MethodSource;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import reactor.core.publisher.Mono;
//
//import java.util.stream.Stream;
//
//public class AtestadoIntegrationTest extends BaseIntegrationTest {
//
//    @BeforeAll static void fieldsInicialization(){
//        URI = "/atestados";
//    }
//
//    @ParameterizedTest
//    @MethodSource("durationsValidas")
//    @Order(1)
//    void canRegisterAtestado(int duracao){
//        AtestadoRequestDTO dto = new AtestadoRequestDTO ();
//        dto.setDuracao ( duracao );
//        dto.setDescricao ( "dor de barriga" );
//
//
//        webTestClient
//                .post ( )
//                .uri ( URI )
//                .header ( "Authorization", "Bearer " + authToken )
//                .accept ( MediaType.APPLICATION_JSON )
//                .contentType ( MediaType.APPLICATION_JSON )
//                .body ( Mono.just ( dto ), AtestadoRequestDTO.class )
//                .exchange ( )
//                .expectStatus ( ).isCreated ( )
//                .expectBody ( Atestado.class )
//                .returnResult ( )
//                .getResponseBody ( );
//
//    }
//
//    @ParameterizedTest
//    @MethodSource("durationsInvalidas")
//    @Order(2)
//    void cannotRegisterAtestadoWithOutOfRangeDuracao (int duracao){
//        AtestadoRequestDTO dto = new AtestadoRequestDTO ();
//        dto.setDuracao ( duracao );
//        dto.setDescricao ( "dor de barriga" );
//
//
//        webTestClient
//                .post ( )
//                .uri ( URI )
//                .header ( "Authorization", "Bearer " + authToken )
//                .accept ( MediaType.APPLICATION_JSON )
//                .contentType ( MediaType.APPLICATION_JSON )
//                .body ( Mono.just ( dto ), AtestadoRequestDTO.class )
//                .exchange ( )
//                .expectStatus ( ).isEqualTo ( HttpStatus.CONFLICT );
//    }
//    @Test
//    @Order(3)
//    void canGetAll(){
//        webTestClient
//                .get ()
//                .uri(URI)
//                .header("Authorization", "Bearer " + authToken)
//                .exchange ()
//                .expectStatus ()
//                .isOk ();
//    }
//
//    @Test
//    @Order(4)
//    void canGetById(){
//
//        Atestado createdObject = createAtestado();
//
//        assert createdObject != null;
//
//        webTestClient
//                .get ()
//                .uri(URI + "/" + createdObject.getId ())
//                .header("Authorization", "Bearer " + authToken)
//                .exchange ()
//                .expectStatus ()
//                .isOk ();
//    }
//
//    @Test
//    @Order(5)
//    void canDelete(){
//        Atestado createdObject = createAtestado();
//        assert createdObject != null;
//        webTestClient
//                .delete ()
//                .uri(URI + "/" + createdObject.getId () )
//                .header("Authorization", "Bearer " + authToken)
//                .exchange ()
//                .expectStatus ()
//                .isNoContent ();
//    }
//
//    // Método estático que fornece dados para o teste
//    static Stream<Integer> durationsInvalidas() {
//        return Stream.of(-1, 0, 31, 40);
//    }
//
//    static Stream<Integer> durationsValidas() {
//        return Stream.of(1, 2, 5, 30, 29);
//    }
//
//    private Atestado createAtestado() {
//        AtestadoRequestDTO dto = new AtestadoRequestDTO ();
//        dto.setDuracao ( 10 );
//        dto.setDescricao ( "dor de barriga" );
//
//        Atestado createdObject = webTestClient
//                .post ( )
//                .uri ( URI )
//                .header ( "Authorization", "Bearer " + authToken )
//                .accept ( MediaType.APPLICATION_JSON )
//                .contentType ( MediaType.APPLICATION_JSON )
//                .body ( Mono.just ( dto ), AtestadoRequestDTO.class )
//                .exchange ( )
//                .expectBody ( Atestado.class )
//                .returnResult ( )
//                .getResponseBody ( );
//
//        assert createdObject != null;
//
//        return createdObject;
//    }
//}
