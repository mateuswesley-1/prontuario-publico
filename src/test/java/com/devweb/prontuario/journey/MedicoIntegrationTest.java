package com.devweb.prontuario.journey;

import com.devweb.prontuario.BaseIntegrationTest;
import com.devweb.prontuario.dto.Funcionario.FuncionarioRequestDTO;
import com.devweb.prontuario.dto.medico.MedicoRequestDTO;
import com.devweb.prontuario.entities.Funcionario;
import com.devweb.prontuario.entities.Medico;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
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
        dto.setEspecialidade ( "testando" );
        dto.setCrm ( 10023 );
        Funcionario funcionarioMedico = getCreatedFuncionario ();
        dto.setFuncionarioId ( funcionarioMedico.getId () );

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
                .expectStatus ();
    }

    private Medico getMedico() {
        Funcionario funcionario = getCreatedFuncionario ();
        MedicoRequestDTO dto = new MedicoRequestDTO ();
        dto.setEspecialidade ( "testando" );
        dto.setCrm ( 10023 );
        dto.setFuncionarioId ( funcionario.getId () );

        return webTestClient
                .post ( )
                .uri ( "/medicos" )
                .header ( "Authorization", "Bearer " + authToken )
                .accept ( MediaType.APPLICATION_JSON )
                .contentType ( MediaType.APPLICATION_JSON )
                .body ( Mono.just ( dto ), MedicoRequestDTO.class )
                .exchange ( )
                .expectBody ( Medico.class )
                .returnResult ( )
                .getResponseBody ( );
    }

    private Funcionario getCreatedFuncionario() {
        FuncionarioRequestDTO dto = new FuncionarioRequestDTO ();
        dto.setNome ( "nome_teste" );
        dto.setCargo ( "cargo_teste" );
        dto.setCPF ( "43562054050" );
        dto.setEmail ( "email_test"  + UUID.randomUUID ( ).toString ().substring ( 0, 5 ) + "@gmail.com");
        dto.setEndereco ( "rua teste" );
        dto.setDataNascimento ( LocalDate.now ( ) );


        Funcionario funcionario = webTestClient
                .post ( )
                .uri ( "/funcionarios" )
                .header ( "Authorization", "Bearer " + authToken )
                .accept ( MediaType.APPLICATION_JSON )
                .contentType ( MediaType.APPLICATION_JSON )
                .body ( Mono.just ( dto ), FuncionarioRequestDTO.class )
                .exchange ( )
                .expectStatus ( ).isCreated ( )
                .expectBody ( Funcionario.class )
                .returnResult ( )
                .getResponseBody ( );

        assert funcionario != null;
        return funcionario;

    }

}
