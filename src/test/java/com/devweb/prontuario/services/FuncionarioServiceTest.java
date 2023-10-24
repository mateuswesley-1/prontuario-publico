package com.devweb.prontuario.services;

import com.devweb.prontuario.entities.Funcionario;
import com.devweb.prontuario.exceptions.CustomExceptions.EntityNotFoundException;
import com.devweb.prontuario.repositories.FuncionarioRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
class FuncionarioServiceTest {
    private FuncionarioService underTest;
    @Mock
    private FuncionarioRepository repository;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        this.autoCloseable = MockitoAnnotations.openMocks ( this );
        this.underTest = new FuncionarioService ( this.repository );
    }
    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close ();
    }

    // getAll : Caso de teste 1: Tem dados, deve retornar esses dados.
    @Test
    void getAllShouldReturnRepositoryResultWithoutChanges() {
        Funcionario funcionario = new Funcionario (
                "cpf",
                "nome",
                LocalDate.now ( ),
                "endereco",
                "email",
                "cargo"
        );
        Page<Funcionario> expected = new PageImpl<> ( List.of ( funcionario ) );
        //Given
        Pageable pageable = PageRequest.of ( 1, 10 );
        when ( this.repository.findAll ( pageable ) ).thenReturn ( expected );

        //When
        Page<Funcionario> result = underTest.getAll ( pageable );

        //Then: Verifica se está chamando o método certo do repo
        verify(this.repository).findAll ( pageable );

        // And: O resultado retornado pelo service é o mesmo do repo.
        assertEquals ( expected, result);
    }
    // getAll : Caso de teste 2: não tem dados, deve retornar array vazia.
    @Test
    void getAllShouldReturnEmptyArrayWhenRepoDontReturnNothing() {
        //Given
        Page<Funcionario> expected = new PageImpl<> ( List.of (  ) );
        Pageable pageable = PageRequest.of ( 1, 10 );
        when ( this.repository.findAll ( pageable ) ).thenReturn ( expected );

        //When
        Page<Funcionario> result = underTest.getAll ( pageable );

        //Then: Verifica se está chamando o método certo do repo
        verify(this.repository).findAll ( pageable );

        // And: O resultado retornado pelo service é o mesmo do repo.
        assertEquals ( expected, result);
    }
    // getById : Caso de teste 1: id correto deve retornar dado correto
    @Test
    void getByIdShouldReturnRepositoryResultWithoutChanges() {
        //Given
        Funcionario expected = new Funcionario (
                "cpf",
                "nome",
                LocalDate.now ( ),
                "endereco",
                "email",
                "cargo"
        );
        when ( this.repository.findById ( "id" ) ).thenReturn ( Optional.of ( expected ) );

        //When
        Funcionario result = this.underTest.getById ( "id" );

        //Then
        verify ( this.repository ).findById ( "id" );
        //And
        assertEquals ( expected, result );
    }

    // getById : Caso de teste 2: id errado deve lançar erro
    @Test
    void getByIdShouldThrowErrorWhenIdDoesNotExist() {
        //Given
        Funcionario expected = new Funcionario (
                "cpf",
                "nome",
                LocalDate.now ( ),
                "endereco",
                "email",
                "cargo"
        );
        when ( this.repository.findById ( "id_certo" ) ).thenReturn ( Optional.of ( expected ) );

        //When -> Then
        Throwable throwable =
                assertThrows (
                        EntityNotFoundException.class,
                        () ->  this.underTest.getById ( "id_errado" )
                );
        assertEquals ( Funcionario.class.getName () + " não encontrado.", throwable.getMessage () );
    }

    // add : Caso de teste 1: objeto deve ser passado corretamente para o repositório
    @Test
    void addShouldPassObjectToRepository() {
        //Given
        Funcionario expected = new Funcionario (
                "cpf",
                "nome",
                LocalDate.now ( ),
                "endereco",
                "email",
                "cargo"
        );

        Funcionario result = new Funcionario (
                expected.getCpf (),
                expected.getNome (),
                expected.getDataNascimento (),
                expected.getEndereco (),
                expected.getEmail (),
                expected.getCargo ()
        );

        result.setId ( UUID.randomUUID ().toString () );
        when( this.repository.save ( expected )).thenReturn ( Optional.of(result) );

        //When
        Funcionario serviceResult = this.underTest.create ( expected );

        //Then
        verify ( this.repository ).save ( expected );
        assertEquals ( result, serviceResult );

    }
    @Test
    void delete() {
        when(this.repository.findById ( "funcionario_id" ))
                .thenReturn ( Optional.of ( new Funcionario (
                        "cpf",
                        "nome",
                        LocalDate.now ( ),
                        "endereco",
                        "email",
                        "cargo"
                ) ) );
        //When
        this.underTest.delete ( "funcionario_id" );
        //Then
        verify ( this.repository ).delete ( "funcionario_id" );
    }

    @Test
    void update() {
        //Given:  recebe um id, e novos dados
        Funcionario att = new Funcionario (
                "cpf",
                "nome",
                LocalDate.now ( ),
                "endereco",
                "email",
                "cargo"
        );
        Funcionario atual = new Funcionario (
                "cpf",
                "nome",
                LocalDate.now ( ),
                "endereco",
                "email",
                "cargo"
        );
        when ( this.repository.findById ( "funcionario_id") ).thenReturn ( Optional.of ( atual ) );

        //When: deve chamar repo.save com os novos dados
        this.underTest.update ( "funcionario_id", att );

        //Then: o obj deve ter seus dados atualizados e ter upDatedAt
        verify ( this.repository ).save ( atual );
        assertNotNull (atual.getUpdated_at ());
    }
}