package com.devweb.prontuario.journey;


import com.devweb.prontuario.dto.credenciais.CredenciaisDTO;
import com.devweb.prontuario.dto.medicamento.MedicamentoRequestDTO;
import com.devweb.prontuario.dto.prescricao.PrescricaoRequestDTO;
import com.devweb.prontuario.entities.Medicamento;
import com.devweb.prontuario.entities.Prescricao;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder( MethodOrderer.OrderAnnotation.class )
public class PrescricaoIntegrationTest {
    @Autowired
    private WebTestClient webTestClient;
    private String authToken;
    private static final String URI = "/prescricoes";
    private static String uuid;
    @BeforeEach
    void login(){
        CredenciaisDTO dto = new CredenciaisDTO ();
        dto.setUsername ( "mateus" );
        dto.setPassword ( "mateus" );

        this.authToken = webTestClient.post()
                .uri("/token") // Endpoint para autenticação
                .contentType( MediaType.APPLICATION_JSON)
                .bodyValue(dto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();
    }
    @Test
    @Order(1)
    void canRegister(){
        MedicamentoRequestDTO dtoMedicamento = new MedicamentoRequestDTO ();
        dtoMedicamento.setNome ( "nome_teste" );
        dtoMedicamento.setDose ( 200.0f );

        Medicamento createdMedicamento = webTestClient
                .post ( )
                .uri ( "/medicamentos" )
                .header ( "Authorization", "Bearer " + authToken )
                .accept ( MediaType.APPLICATION_JSON )
                .contentType ( MediaType.APPLICATION_JSON )
                .body ( Mono.just ( dtoMedicamento ), MedicamentoRequestDTO.class )
                .exchange ( )
                .expectStatus ( ).isCreated ( )
                .expectBody ( Medicamento.class )
                .returnResult ( )
                .getResponseBody ( );



        PrescricaoRequestDTO dto = new PrescricaoRequestDTO ();
        dto.setFrequenciaHoras ( 10 );
        dto.setQtdDias ( 100 );
        assert createdMedicamento != null;
        dto.setIdMedicamento ( createdMedicamento.getId () );

        Prescricao createdObject = webTestClient
                .post ( )
                .uri ( URI )
                .header ( "Authorization", "Bearer " + authToken )
                .accept ( MediaType.APPLICATION_JSON )
                .contentType ( MediaType.APPLICATION_JSON )
                .body ( Mono.just ( dto ), PrescricaoRequestDTO.class )
                .exchange ( )
                .expectStatus ( ).isCreated ( )
                .expectBody ( Prescricao.class )
                .returnResult ( )
                .getResponseBody ( );

        assert createdObject != null;
        uuid = createdObject.getId ();
    }

    @Test
    @Order(2)
    void canGetAll(){
        webTestClient
                .get ()
                .uri(URI)
                .header("Authorization", "Bearer " + authToken)
                .exchange ()
                .expectStatus ()
                .isOk ();
    }

    @Test
    @Order(3)
    void canGetById(){
        webTestClient
                .get ()
                .uri(URI + "/" + uuid)
                .header("Authorization", "Bearer " + authToken)
                .exchange ()
                .expectStatus ()
                .isOk ();
    }

    @Test
    @Order(4)
    void canDelete(){
        webTestClient
                .delete ()
                .uri(URI + "/" + uuid )
                .header("Authorization", "Bearer " + authToken)
                .exchange ()
                .expectStatus ()
                .isNoContent ();
    }
}
