package com.devweb.prontuario.journey;


import com.devweb.prontuario.BaseIntegrationTest;
import com.devweb.prontuario.dto.medicamento.MedicamentoRequestDTO;
import com.devweb.prontuario.dto.prescricao.PrescricaoRequestDTO;
import com.devweb.prontuario.entities.Medicamento;
import com.devweb.prontuario.entities.Prescricao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;

public class PrescricaoIntegrationTest extends BaseIntegrationTest {
    @BeforeAll static void fieldsInicialization(){
        URI = "/prescricoes";
    }

    @Test
    @Order(1)
    void canRegister(){
        Medicamento createdMedicamento = getCreatedMedicamento ( );

        PrescricaoRequestDTO dto = new PrescricaoRequestDTO ();
        dto.setFrequenciaHoras ( 10 );
        dto.setQtdDias ( 100 );
        dto.setIdMedicamento ( createdMedicamento.getId () );

        webTestClient
                .post ( )
                .uri ( URI )
                .header ( "Authorization", "Bearer " + authToken )
                .accept ( MediaType.APPLICATION_JSON )
                .contentType ( MediaType.APPLICATION_JSON )
                .body ( Mono.just ( dto ), PrescricaoRequestDTO.class )
                .exchange ( )
                .expectStatus ( ).isCreated ( );
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
        Prescricao createdObject = getCreatedPrescricao ();
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
        Prescricao createdObject = getCreatedPrescricao ();
        webTestClient
                .delete ()
                .uri(URI + "/" + createdObject.getId () )
                .header("Authorization", "Bearer " + authToken)
                .exchange ()
                .expectStatus ()
                .isNoContent ();
    }

    private Medicamento getCreatedMedicamento() {
        MedicamentoRequestDTO dtoMedicamento = new MedicamentoRequestDTO ();
        dtoMedicamento.setNome ( "nome_teste" );
        dtoMedicamento.setDose ( 200.0f );

        return webTestClient
                .post ( )
                .uri ( "/medicamentos" )
                .header ( "Authorization", "Bearer " + authToken )
                .accept ( MediaType.APPLICATION_JSON )
                .contentType ( MediaType.APPLICATION_JSON )
                .body ( Mono.just ( dtoMedicamento ), MedicamentoRequestDTO.class )
                .exchange ( )
                .expectBody ( Medicamento.class )
                .returnResult ( )
                .getResponseBody ( );
    }

    private Prescricao getCreatedPrescricao() {
        Medicamento createdMedicamento = getCreatedMedicamento ( );

        PrescricaoRequestDTO dto = new PrescricaoRequestDTO ();
        dto.setFrequenciaHoras ( 10 );
        dto.setQtdDias ( 100 );
        dto.setIdMedicamento ( createdMedicamento.getId () );

        return webTestClient
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
    }
}
