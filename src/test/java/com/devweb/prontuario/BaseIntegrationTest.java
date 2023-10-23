package com.devweb.prontuario;

import com.devweb.prontuario.dto.credenciais.CredenciaisDTO;
import com.devweb.prontuario.entities.Credenciais;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.UUID;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder ( OrderAnnotation.class )
public class BaseIntegrationTest {
    @Autowired
    protected WebTestClient webTestClient;
    protected static String URI;
    protected String authToken;
    private Credenciais credenciais;

    private final String login_user = "test_user" + UUID.randomUUID ( );
    private final String login_password = "test_password";
    @BeforeEach
    void login(){
        CredenciaisDTO dto = new CredenciaisDTO ();
        dto.setUsername ( login_user );
        dto.setPassword ( login_password );
        if ( this.credenciais == null ){
            this.credenciais = webTestClient.post()
                    .uri ( "/credenciais" )
                    .contentType( MediaType.APPLICATION_JSON)
                    .bodyValue(dto)
                    .exchange()
                    .expectStatus().isCreated ( )
                    .expectBody(Credenciais.class)
                    .returnResult()
                    .getResponseBody();
        }

        if ( this.authToken == null ){
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

    }
}
