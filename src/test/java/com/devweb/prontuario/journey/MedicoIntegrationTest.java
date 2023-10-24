package com.devweb.prontuario.journey;

import com.devweb.prontuario.BaseIntegrationTest;
import com.devweb.prontuario.dto.medico.MedicoRequestDTO;
import com.devweb.prontuario.entities.Medico;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

public class MedicoIntegrationTest extends BaseIntegrationTest {
    @BeforeAll static void fieldsInicialization(){
        URI = "/medicos";
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

        webTestClient
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
        Medico createdObject = getMedico();
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
        Medico createdObject = getMedico();
        webTestClient
                .delete ()
                .uri(URI + "/" + createdObject.getId () )
                .header("Authorization", "Bearer " + authToken)
                .exchange ()
                .expectStatus ()
                .isNoContent ();
    }

    private Medico getMedico() {
        MedicoRequestDTO dto = new MedicoRequestDTO ();
        dto.setNome ( "nome_teste" );
        dto.setCPF ( "cpf_test" );
        dto.setEmail ( "email_test" + UUID.randomUUID ( ));
        dto.setEndereco ( "rua teste" );
        dto.setDataNascimento ( LocalDate.now ( ) );
        dto.setEspecialidade ( "testando" );
        dto.setCrm ( 10023 );


        return webTestClient
                .post ( )
                .uri ( URI )
                .header ( "Authorization", "Bearer " + authToken )
                .accept ( MediaType.APPLICATION_JSON )
                .contentType ( MediaType.APPLICATION_JSON )
                .body ( Mono.just ( dto ), MedicoRequestDTO.class )
                .exchange ( )
                .expectBody ( Medico.class )
                .returnResult ( )
                .getResponseBody ( );
    }

}
