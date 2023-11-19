//package com.devweb.prontuario.repositories.jbdc;
//
//import com.devweb.prontuario.base.BaseMapper;
//import com.devweb.prontuario.BaseTestContainers;
//import com.devweb.prontuario.dto.credenciais.CredenciaisRequestDTO;
//import com.devweb.prontuario.dto.credenciais.CredenciaisResponseDTO;
//import com.devweb.prontuario.entities.Credenciais;
//
//import org.jetbrains.annotations.NotNull;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.modelmapper.ModelMapper;
//
//import org.springframework.dao.DuplicateKeyException;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class CredenciaisRepositoryTest extends BaseTestContainers{
//    private CredenciaisRepositoryJbdc underTest;
//    private NamedParameterJdbcTemplate template;
//    private BaseMapper<Credenciais, CredenciaisRequestDTO, CredenciaisResponseDTO> credenciaisMapper;
//
//    @BeforeEach
//    void setUp() {
//        this.template = this.getJbdcTemplate ( );
//        this.underTest =
//                new CredenciaisRepositoryJbdc (
//                    this.template
//                );
//        this.credenciaisMapper =
//                new BaseMapper<> (
//                    new ModelMapper ()
//                );
//    }
//
//    // Não deve ser possível saber os usuários existentes.
//    @Test
//    void findAllShouldThrowMethodNotAllowedException() {
//        //Given
//        Pageable pageable = PageRequest.of ( 1, 1 );
//
//        //When
//        Throwable throwable =
//                assertThrows (
//                        UnsupportedOperationException.class,
//                        () ->  this.underTest.findAll ( pageable )
//                );
//        //Then
//        assertEquals ( "Esse método não é permitido para um objeto da classe " + Credenciais.class, throwable.getMessage () );
//    }
//    @Test
//    void findByIdShouldThrowMethodNotAllowedException() {
//        //Given
//        String id = "credenciais_id";
//
//        //When
//        Throwable throwable =
//                assertThrows (
//                        UnsupportedOperationException.class,
//                        () ->  this.underTest.findById ( id )
//                );
//        //Then
//        assertEquals ( "Esse método não é permitido para um objeto da classe " + Credenciais.class, throwable.getMessage () );
//
//    }
//    // Deve ser possível se cadastrar se o usuário for válido.
//    @Test
//    void saveShouldCreateCredentialsIfUserValid(){
//
//        //Given
//        String username = "rodolfo";
//        String password = "rodolfo";
//        CredenciaisRequestDTO dto = new CredenciaisRequestDTO ();
//        dto.setUsername ( username );
//        dto.setPassword ( password );
//
//        Credenciais credenciais = this.credenciaisMapper.requestDtoToEntity ( dto, Credenciais.class);
//        //When
//        this.underTest.save (credenciais);
//
//        Optional<Credenciais> result = getCredenciais ( username );
//
//        //Then
//        assertTrue(result.isPresent ());
//        assertEquals ( username, result.get ().getUsername () );
//    }
//    @Test
//    void saveShouldNotCreateCredentialsIfUserNotUnique() {
//        //Given
//        String username = "user_test_3";
//        String password = "user_test_3";
//        CredenciaisRequestDTO dto = new CredenciaisRequestDTO ();
//        dto.setUsername ( username );
//        dto.setPassword ( password );
//
//        Credenciais credenciais = this.credenciaisMapper.requestDtoToEntity ( dto, Credenciais.class);
//        this.underTest.save (credenciais);
//        //When
//
//        //When
//            assertThrows (
//                    DuplicateKeyException.class,
//                    () ->  this.underTest.save (credenciais)
//            );
//    }
//    @Test
//    void EntityShouldNotBePresentWhenDeleted() {
//        //Given
//        String username = "fernando";
//        String password = "fernando";
//        CredenciaisRequestDTO dto = new CredenciaisRequestDTO ();
//        dto.setUsername ( username );
//        dto.setPassword ( password );
//
//        Credenciais credenciais = this.credenciaisMapper.requestDtoToEntity ( dto, Credenciais.class);
//        this.underTest.save ( credenciais );
//
//        //When
//        this.underTest.delete (this.getCredenciais ( username ).get ().getId ());
//
//        //Then
//        assertTrue(this.getCredenciais ( username ).isEmpty ());
//    }
//    @NotNull
//    private Optional<Credenciais> getCredenciais(String username) {
//        String sql = "SELECT * FROM credenciais_tb  WHERE username = ? AND deleted_at is null";
//
//        RowMapper<Credenciais> rowMapper = this.underTest.getRowMapper ( );
//        return template
//                .getJdbcOperations()
//                .query(sql, rowMapper, username )
//                .stream()
//                .findFirst();
//    }
//}