package com.devweb.prontuario.journey;

import com.devweb.prontuario.dto.credenciais.CredenciaisDTO;
import com.devweb.prontuario.dto.medico.MedicoRequestDTO;
import com.devweb.prontuario.entities.Medico;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder( MethodOrderer.OrderAnnotation.class )
public class MedicoIntegrationTest {
    @Autowired
    private WebTestClient webTestClient;
    private String authToken;
    private static final String URI = "/medicos";
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
        MedicoRequestDTO dto = new MedicoRequestDTO ();
        dto.setNome ( "nome_teste" );
        dto.setCPF ( "cpf_test" );
        dto.setEmail ( "email_test" + UUID.randomUUID ( ));
        dto.setEndereco ( "rua teste" );
        dto.setDataNascimento ( LocalDate.now ( ) );
        dto.setEspecialidade ( "testando" );
        dto.setCrm ( 10023 );

        Medico createdObject = webTestClient
                .post ( )
                .uri ( URI )
                .header ( "Authorization", "Bearer " + authToken )
                .accept ( MediaType.APPLICATION_JSON )
                .contentType ( MediaType.APPLICATION_JSON )
                .body ( Mono.just ( dto ), MedicoRequestDTO.class )
                .exchange ( )
                .expectStatus ( ).isCreated ( )
                .expectBody ( Medico.class )
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
