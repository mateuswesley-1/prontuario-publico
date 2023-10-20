package com.devweb.prontuario.journey;

import com.devweb.prontuario.dto.Funcionario.FuncionarioRequestDTO;
import com.devweb.prontuario.dto.credenciais.CredenciaisDTO;
import com.devweb.prontuario.entities.Funcionario;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder( MethodOrderer.OrderAnnotation.class )
public class FuncionarioIntegrationTest {
    
    @Autowired
    private WebTestClient webTestClient;

    private String authToken;
    private static final String URI = "/funcionarios";
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
        FuncionarioRequestDTO dto = new FuncionarioRequestDTO ();
        dto.setNome ( "nome_teste" );
        dto.setCargo ( "cargo_teste" );
        dto.setCPF ( "cpf_test" );
        dto.setEmail ( "email_test" );
        dto.setEndereco ( "rua teste" );
        dto.setDataNascimento ( LocalDate.now ( ) );


        Funcionario createdObject = webTestClient
                .post ( )
                .uri ( URI )
                .header ( "Authorization", "Bearer " + authToken )
                .accept ( MediaType.APPLICATION_JSON )
                .contentType ( MediaType.APPLICATION_JSON )
                .body ( Mono.just ( dto ), FuncionarioRequestDTO.class )
                .exchange ( )
                .expectStatus ( ).isCreated ( )
                .expectBody ( Funcionario.class )
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
