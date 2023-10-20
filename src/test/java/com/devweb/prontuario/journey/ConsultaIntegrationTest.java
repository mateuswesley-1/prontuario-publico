package com.devweb.prontuario.journey;

import com.devweb.prontuario.dto.Funcionario.FuncionarioRequestDTO;
import com.devweb.prontuario.dto.atestado.AtestadoRequestDTO;
import com.devweb.prontuario.dto.consulta.ConsultaRequestDTO;
import com.devweb.prontuario.dto.credenciais.CredenciaisDTO;
import com.devweb.prontuario.dto.medico.MedicoRequestDTO;
import com.devweb.prontuario.entities.Atestado;
import com.devweb.prontuario.entities.Consulta;
import com.devweb.prontuario.entities.Funcionario;
import com.devweb.prontuario.entities.Medico;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder( MethodOrderer.OrderAnnotation.class )
public class ConsultaIntegrationTest {
    @Autowired
    private WebTestClient webTestClient;

    private String authToken;
    private static final String URI = "/consultas";
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


        // Criando atestado
        AtestadoRequestDTO dtoAtestado = new AtestadoRequestDTO ();
        dtoAtestado.setDescricao ( "teste" );
        dtoAtestado.setDuracao ( 10 );

        Atestado createdAtestado = webTestClient
                .post ( )
                .uri ( "/atestados" )
                .header ( "Authorization", "Bearer " + authToken )
                .accept ( MediaType.APPLICATION_JSON )
                .contentType ( MediaType.APPLICATION_JSON )
                .body ( Mono.just ( dtoAtestado ), AtestadoRequestDTO.class )
                .exchange ( )
                .expectStatus ( ).isCreated ( )
                .expectBody ( Atestado.class )
                .returnResult ( )
                .getResponseBody ( );

        FuncionarioRequestDTO dtoPaciente = new FuncionarioRequestDTO ();
        dtoPaciente.setNome ( "nome_teste" );
        dtoPaciente.setCargo ( "cargo_teste" );
        dtoPaciente.setCPF ( "cpf_test" );
        dtoPaciente.setEmail ( "email_test" +  UUID.randomUUID ( ) );
        dtoPaciente.setEndereco ( "rua teste" );
        dtoPaciente.setDataNascimento ( LocalDate.now ( ) );

        Funcionario createdFuncionario = webTestClient
                .post ( )
                .uri ( "/funcionarios" )
                .header ( "Authorization", "Bearer " + authToken )
                .accept ( MediaType.APPLICATION_JSON )
                .contentType ( MediaType.APPLICATION_JSON )
                .body ( Mono.just ( dtoPaciente ), FuncionarioRequestDTO.class )
                .exchange ( )
                .expectStatus ( ).isCreated ( )
                .expectBody ( Funcionario.class )
                .returnResult ( )
                .getResponseBody ( );

        MedicoRequestDTO dtoMedico = new MedicoRequestDTO (  );
        dtoMedico.setCPF ( "cpf" );
        dtoMedico.setNome ( "teste" );
        dtoMedico.setEmail ( "email@teste" + UUID.randomUUID ( )  );
        dtoMedico.setEndereco ( "endereco" );
        dtoMedico.setDataNascimento ( LocalDate.now () );
        dtoMedico.setCrm ( 1545 );
        dtoMedico.setEspecialidade ( "teste" );

        Medico createdMedico = webTestClient
                .post ( )
                .uri ( "/medicos" )
                .header ( "Authorization", "Bearer " + authToken )
                .accept ( MediaType.APPLICATION_JSON )
                .contentType ( MediaType.APPLICATION_JSON )
                .body ( Mono.just ( dtoMedico ), MedicoRequestDTO.class )
                .exchange ( )
                .expectStatus ( ).isCreated ( )
                .expectBody ( Medico.class )
                .returnResult ( )
                .getResponseBody ( );


        ConsultaRequestDTO dto = new ConsultaRequestDTO ();
        dto.setAnamnese ( "teste" );
        assert createdAtestado != null;
        dto.setIdAtestado ( createdAtestado.getId () );
        assert createdFuncionario != null;
        dto.setIdPaciente ( createdFuncionario.getId () );
        dto.setPrescricoes ( List.of ("presc1", "presc2", "presc3")  );
        assert createdMedico != null;
        dto.setIdMedico ( createdMedico.getId () );

        Consulta createdObject = webTestClient
                .post ( )
                .uri ( URI )
                .header ( "Authorization", "Bearer " + authToken )
                .accept ( MediaType.APPLICATION_JSON )
                .contentType ( MediaType.APPLICATION_JSON )
                .body ( Mono.just ( dto ), ConsultaRequestDTO.class )
                .exchange ( )
                .expectStatus ( ).isCreated ( )
                .expectBody ( Consulta.class )
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
