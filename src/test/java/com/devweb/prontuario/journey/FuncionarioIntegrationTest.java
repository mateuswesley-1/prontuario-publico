package com.devweb.prontuario.journey;

import com.devweb.prontuario.BaseIntegrationTest;
import com.devweb.prontuario.dto.Funcionario.FuncionarioRequestDTO;
import com.devweb.prontuario.entities.Funcionario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

public class FuncionarioIntegrationTest extends BaseIntegrationTest {
    @BeforeAll static void fieldsInicialization(){
        URI = "/funcionarios";
    }

    @Test
    @Order(1)
    void canRegister(){
        FuncionarioRequestDTO dto = new FuncionarioRequestDTO ();
        dto.setNome ( "nome_teste" );
        dto.setCargo ( "cargo_teste" );
        dto.setCPF ( "cpf_test" );
        dto.setEmail ( "email_test"  + UUID.randomUUID ( ));
        dto.setEndereco ( "rua teste" );
        dto.setDataNascimento ( LocalDate.now ( ) );


        webTestClient
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
        Funcionario createdObject = getCreatedObject();
        webTestClient
                .get ()
                .uri(URI + "/" + createdObject.getId ())
                .header("Authorization", "Bearer " + authToken)
                .exchange ()
                .expectStatus ()
                .isOk ();
    }

    @Test
    @Order(4)
    void canDelete(){
        Funcionario createdObject = getCreatedObject();
        webTestClient
                .delete ()
                .uri(URI + "/" + createdObject.getId () )
                .header("Authorization", "Bearer " + authToken)
                .exchange ()
                .expectStatus ()
                .isNoContent ();
    }

    private Funcionario getCreatedObject() {
        FuncionarioRequestDTO dto = new FuncionarioRequestDTO ();
        dto.setNome ( "nome_teste" );
        dto.setCargo ( "cargo_teste" );
        dto.setCPF ( "cpf_test" );
        dto.setEmail ( "email_test"  + UUID.randomUUID ( ));
        dto.setEndereco ( "rua teste" );
        dto.setDataNascimento ( LocalDate.now ( ) );


        return webTestClient
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

    }
    
}
