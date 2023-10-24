package com.devweb.prontuario.services;

import com.devweb.prontuario.entities.Medico;
import com.devweb.prontuario.exceptions.CustomExceptions.EntityNotFoundException;
import com.devweb.prontuario.repositories.MedicoRepository;
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

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
class MedicoServiceTest  {
    private MedicoService underTest;
    @Mock
    private MedicoRepository repository;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        this.autoCloseable = MockitoAnnotations.openMocks ( this );
        this.underTest = new MedicoService ( this.repository );
    }
    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close ();
    }

    // getAll : Caso de teste 1: Tem dados, deve retornar esses dados.
    @Test
    void getAllShouldReturnRepositoryResultWithoutChanges() {
        Medico medico = new Medico (
                "funcionario_id",
                100,
                "protologista"
        );
        Page<Medico> expected = new PageImpl<> ( List.of ( medico ) );
        //Given
        Pageable pageable = PageRequest.of ( 1, 10 );
        when ( this.repository.findAll ( pageable ) ).thenReturn ( expected );

        //When
        Page<Medico> result = underTest.getAll ( pageable );

        //Then: Verifica se está chamando o método certo do repo
        verify(this.repository).findAll ( pageable );

        // And: O resultado retornado pelo service é o mesmo do repo.
        assertEquals ( expected, result);
    }
    // getAll : Caso de teste 2: não tem dados, deve retornar array vazia.
    @Test
    void getAllShouldReturnEmptyArrayWhenRepoDontReturnNothing() {
        //Given
        Page<Medico> expected = new PageImpl<> ( List.of (  ) );
        Pageable pageable = PageRequest.of ( 1, 10 );
        when ( this.repository.findAll ( pageable ) ).thenReturn ( expected );

        //When
        Page<Medico> result = underTest.getAll ( pageable );

        //Then: Verifica se está chamando o método certo do repo
        verify(this.repository).findAll ( pageable );

        // And: O resultado retornado pelo service é o mesmo do repo.
        assertEquals ( expected, result);
    }
    // getById : Caso de teste 1: id correto deve retornar dado correto
    @Test
    void getByIdShouldReturnRepositoryResultWithoutChanges() {
        //Given
        Medico expected = new Medico (
                "funcionario_id",
                100,
                "protologista"
        );
        when ( this.repository.findById ( "id" ) ).thenReturn ( Optional.of ( expected ) );

        //When
        Medico result = this.underTest.getById ( "id" );

        //Then
        verify ( this.repository ).findById ( "id" );
        //And
        assertEquals ( expected, result );
    }

    // getById : Caso de teste 2: id errado deve lançar erro
    @Test
    void getByIdShouldThrowErrorWhenIdDoesNotExist() {
        //Given
        Medico expected = new Medico (
                "funcionario_id",
                100,
                "protologista"
        );
        when ( this.repository.findById ( "id_certo" ) ).thenReturn ( Optional.of ( expected ) );

        //When -> Then
        Throwable throwable =
                assertThrows (
                        EntityNotFoundException.class,
                        () ->  this.underTest.getById ( "id_errado" )
                );
        assertEquals ( Medico.class.getName () + " não encontrado.", throwable.getMessage () );
    }

    // add : Caso de teste 1: objeto deve ser passado corretamente para o repositório
    @Test
    void addShouldPassObjectToRepositoryAndReturnObjWithId() {
        //Given
        Medico expected = new Medico (
                "funcionario_id",
                100,
                "protologista"
        );

        Medico result =  new Medico ( expected.getFuncionario_id (), expected.getCrm (), expected.getEspecialidade ( ) );
        result.setId ( UUID.randomUUID ().toString () );
        when(this.repository.save ( expected )).thenReturn ( Optional.of(result) );

        //When
        Medico testResult = this.underTest.create ( expected );

        //Then
        verify ( this.repository ).save ( expected );
        assertEquals ( result, testResult );

    }
    @Test
    void delete() {
        when(this.repository.findById ( "medico_id" ))
                .thenReturn ( Optional.of ( new Medico (
                        "funcionario_id",
                        100,
                        "protologista"
                ) ) );
        //When
        this.underTest.delete ( "medico_id" );
        //Then
        verify ( this.repository ).delete ( "medico_id" );
    }

    @Test
    void update() {
        //Given:  recebe um id, e novos dados
        Medico att = new Medico (
                "funcionario_id",
                100,
                "protologista"
        );
        Medico atual = new Medico (
                "funcionario_id",
                100,
                "protologista"
        );
        when ( this.repository.findById ( "medico_id") ).thenReturn ( Optional.of ( atual ) );

        //When: deve chamar repo.save com os novos dados
        this.underTest.update ( "medico_id", att );

        //Then: o obj deve ter seus dados atualizados e ter upDatedAt
        verify ( this.repository ).save ( atual );
        assertNotNull (atual.getUpdated_at ());
    }
}