//package com.devweb.prontuario.journey;
//
//import com.devweb.prontuario.BaseIntegrationTest;
//import com.devweb.prontuario.dto.Funcionario.FuncionarioRequestDTO;
//import com.devweb.prontuario.dto.atestado.AtestadoRequestDTO;
//import com.devweb.prontuario.dto.consulta.ConsultaRequestDTO;
//import com.devweb.prontuario.dto.medico.MedicoRequestDTO;
//import com.devweb.prontuario.entities.Consulta;
//import com.devweb.prontuario.entities.Funcionario;
//import com.devweb.prontuario.entities.Medico;
//import org.jetbrains.annotations.Nullable;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.springframework.http.MediaType;
//import reactor.core.publisher.Mono;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.UUID;
//
//
//public class ConsultaIntegrationTest extends BaseIntegrationTest {
//
//    @BeforeAll static void fieldsInicialization(){
//        URI = "/consultas";
//    }
//    @Test
//    @Order(1)
//    void canRegister(){
//        // Given
//        AtestadoRequestDTO atestadoDto = getCreatedAtestado (  );
//        Funcionario createdFuncionario = getCreatedFuncionario ( );
//        Medico createdMedico = getCreatedMedico ( );
//
//        // When e then
//        ConsultaRequestDTO dto = new ConsultaRequestDTO ();
//        dto.setAnamnese ( "teste" );
//        assert atestadoDto != null;
//        dto.setAtestado ( atestadoDto );
//        assert createdFuncionario != null;
//        dto.setIdPaciente ( createdFuncionario.getId () );
//        dto.setPrescricoes ( List.of ("presc1", "presc2", "presc3")  );
//        assert createdMedico != null;
//        dto.setIdMedico ( createdMedico.getId () );
//
//        webTestClient
//                .post ( )
//                .uri ( URI )
//                .header ( "Authorization", "Bearer " + authToken )
//                .accept ( MediaType.APPLICATION_JSON )
//                .contentType ( MediaType.APPLICATION_JSON )
//                .body ( Mono.just ( dto ), ConsultaRequestDTO.class )
//                .exchange ( )
//                .expectStatus ( ).isCreated ( )
//                .expectBody ( Consulta.class );
//    }
//
//    @Test
//    @Order(2)
//    void canGetAll(){
//        webTestClient
//                .get ()
//                .uri(URI)
//                .header("Authorization", "Bearer " + authToken)
//                .exchange ()
//                .expectStatus ()
//                .isOk ();
//    }
//
//    @Test
//    @Order(3)
//    void canGetById(){
//        Consulta createdConsulta = createConsulta();
//        webTestClient
//                .get ()
//                .uri(URI + "/" + createdConsulta.getId ())
//                .header("Authorization", "Bearer " + authToken)
//                .exchange ()
//                .expectStatus ()
//                .isOk ();
//    }
//
//    @Test
//    @Order(4)
//    void canDelete(){
//        Consulta createdConsulta = createConsulta();
//        webTestClient
//                .delete ()
//                .uri(URI + "/" + createdConsulta.getId () )
//                .header("Authorization", "Bearer " + authToken)
//                .exchange ()
//                .expectStatus ()
//                .isNoContent ();
//    }
//
//    private Consulta createConsulta() {
//        // When
//        // Criando atestado
//        AtestadoRequestDTO atestadoDto = getCreatedAtestado (  );
//
//        // Criando funcionario
//        Funcionario createdFuncionario = getCreatedFuncionario ( );
//
//        // Criando medico
//        Medico createdMedico = getCreatedMedico ( );
//
//
//        ConsultaRequestDTO dto = new ConsultaRequestDTO ();
//        dto.setAnamnese ( "teste" );
//        assert atestadoDto != null;
//        dto.setAtestado ( atestadoDto );
//        assert createdFuncionario != null;
//        dto.setIdPaciente ( createdFuncionario.getId () );
//        dto.setPrescricoes ( List.of ("presc1", "presc2", "presc3")  );
//        assert createdMedico != null;
//        dto.setIdMedico ( createdMedico.getId () );
//
//        return webTestClient
//                .post ( )
//                .uri ( URI )
//                .header ( "Authorization", "Bearer " + authToken )
//                .accept ( MediaType.APPLICATION_JSON )
//                .contentType ( MediaType.APPLICATION_JSON )
//                .body ( Mono.just ( dto ), ConsultaRequestDTO.class )
//                .exchange ( )
//                .expectBody ( Consulta.class )
//                .returnResult ( )
//                .getResponseBody ( );
//    }
//
//    @Nullable
//    private Medico getCreatedMedico() {
//        MedicoRequestDTO dtoMedico = new MedicoRequestDTO (  );
//        Funcionario funcionario = getCreatedFuncionario ();
//        assert funcionario != null;
//        dtoMedico.setFuncionarioId ( funcionario.getId () );
//        dtoMedico.setCrm ( 1545 );
//        dtoMedico.setEspecialidade ( "teste" );
//
//        return webTestClient
//                .post ( )
//                .uri ( "/medicos" )
//                .header ( "Authorization", "Bearer " + authToken )
//                .accept ( MediaType.APPLICATION_JSON )
//                .contentType ( MediaType.APPLICATION_JSON )
//                .body ( Mono.just ( dtoMedico ), MedicoRequestDTO.class )
//                .exchange ( )
//                .expectStatus ( ).isCreated ( )
//                .expectBody ( Medico.class )
//                .returnResult ( )
//                .getResponseBody ( );
//    }
//
//    @Nullable
//    private Funcionario getCreatedFuncionario() {
//        FuncionarioRequestDTO dtoPaciente = new FuncionarioRequestDTO ();
//        dtoPaciente.setNome ( "nome_teste" );
//        dtoPaciente.setCargo ( "cargo_teste" );
//        dtoPaciente.setCPF ( "46639814021" );
//        dtoPaciente.setEmail ( "email" + UUID.randomUUID ( ).toString ().substring ( 1, 5 ) + "@gmail.com"  );
//        dtoPaciente.setEndereco ( "rua teste" );
//        dtoPaciente.setDataNascimento ( LocalDate.now ( ) );
//
//        return webTestClient
//                .post ( )
//                .uri ( "/funcionarios" )
//                .header ( "Authorization", "Bearer " + authToken )
//                .accept ( MediaType.APPLICATION_JSON )
//                .contentType ( MediaType.APPLICATION_JSON )
//                .body ( Mono.just ( dtoPaciente ), FuncionarioRequestDTO.class )
//                .exchange ( )
//                .expectStatus ( ).isCreated ( )
//                .expectBody ( Funcionario.class )
//                .returnResult ( )
//                .getResponseBody ( );
//    }
//
//    private AtestadoRequestDTO getCreatedAtestado() {
//
//        AtestadoRequestDTO dtoAtestado = new AtestadoRequestDTO ();
//        dtoAtestado.setDescricao ( "teste" );
//        dtoAtestado.setDuracao ( 10 );
//        return dtoAtestado;
//    }
//
//}
