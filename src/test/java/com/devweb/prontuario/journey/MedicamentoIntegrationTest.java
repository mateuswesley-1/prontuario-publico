package com.devweb.prontuario.journey;

import com.devweb.prontuario.BaseIntegrationTest;
import com.devweb.prontuario.dto.medicamento.MedicamentoRequestDTO;
import com.devweb.prontuario.entities.Medicamento;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;

public class MedicamentoIntegrationTest extends BaseIntegrationTest {
    @BeforeAll static void fieldsInicialization(){
        URI = "/medicamentos";
    }
    
    @Test
    @Order(1)
    void canRegister(){
        MedicamentoRequestDTO dto = new MedicamentoRequestDTO ();
        dto.setNome ( "nome_teste" );
        dto.setDose ( 200.0f );

        webTestClient
                .post ( )
                .uri ( URI )
                .header ( "Authorization", "Bearer " + authToken )
                .accept ( MediaType.APPLICATION_JSON )
                .contentType ( MediaType.APPLICATION_JSON )
                .body ( Mono.just ( dto ), MedicamentoRequestDTO.class )
                .exchange ( )
                .expectStatus ( ).isCreated ( )
                .expectBody ( Medicamento.class )
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
        Medicamento createdObject = getMedicamento();
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
        Medicamento createdObject = getMedicamento();
        webTestClient
                .delete ()
                .uri(URI + "/" + createdObject.getId () )
                .header("Authorization", "Bearer " + authToken)
                .exchange ()
                .expectStatus ()
                .isNoContent ();
    }

    private Medicamento getMedicamento() {
        MedicamentoRequestDTO dto = new MedicamentoRequestDTO ();
        dto.setNome ( "nome_teste" );
        dto.setDose ( 200.0f );

        return webTestClient
                .post ( )
                .uri ( URI )
                .header ( "Authorization", "Bearer " + authToken )
                .accept ( MediaType.APPLICATION_JSON )
                .contentType ( MediaType.APPLICATION_JSON )
                .body ( Mono.just ( dto ), MedicamentoRequestDTO.class )
                .exchange ( )
                .expectBody ( Medicamento.class )
                .returnResult ( )
                .getResponseBody ( );
    }
}
