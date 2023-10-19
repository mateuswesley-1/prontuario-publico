package com.devweb.prontuario.services;


import com.devweb.prontuario.entities.Consulta;
import com.devweb.prontuario.exceptions.CustomExceptions.EntityNotFoundException;
import com.devweb.prontuario.repositories.ConsultaRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ConsultaServiceTest {
    private ConsultaService underTest;
    @Mock
    private ConsultaRepository repository;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        this.autoCloseable = MockitoAnnotations.openMocks ( this );
        this.underTest = new ConsultaService ( this.repository );
    }
    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close ();
    }

    // getAll : Caso de teste 1: Tem dados, deve retornar esses dados.
    @Test
    void getAllShouldReturnRepositoryResultWithoutChanges() {
        Consulta consulta = new Consulta (
                "medico_id",
                "paciente_id",
                "dor de barriga",
                "atestado_id"
        );
        Page<Consulta> expected = new PageImpl<> ( List.of ( consulta ) );
        //Given
        Pageable pageable = PageRequest.of ( 1, 10 );
        when ( this.repository.findAll ( pageable ) ).thenReturn ( expected );

        //When
        Page<Consulta> result = underTest.getAll ( pageable );

        //Then: Verifica se está chamando o método certo do repo
        verify(this.repository).findAll ( pageable );

        // And: O resultado retornado pelo service é o mesmo do repo.
        assertEquals ( expected, result);
    }
    // getAll : Caso de teste 2: não tem dados, deve retornar array vazia.
    @Test
    void getAllShouldReturnEmptyArrayWhenRepoDontReturnNothing() {
        //Given
        Page<Consulta> expected = new PageImpl<> ( List.of (  ) );
        Pageable pageable = PageRequest.of ( 1, 10 );
        when ( this.repository.findAll ( pageable ) ).thenReturn ( expected );

        //When
        Page<Consulta> result = underTest.getAll ( pageable );

        //Then: Verifica se está chamando o método certo do repo
        verify(this.repository).findAll ( pageable );

        // And: O resultado retornado pelo service é o mesmo do repo.
        assertEquals ( expected, result);
    }
    // getById : Caso de teste 1: id correto deve retornar dado correto
    @Test
    void getByIdShouldReturnRepositoryResultWithoutChanges() {
        //Given
        Consulta expected = new Consulta (
                "medico_id",
                "paciente_id",
                "dor de barriga",
                "atestado_id"
        );
        when ( this.repository.findById ( "id" ) ).thenReturn ( Optional.of ( expected ) );

        //When
        Consulta result = this.underTest.getById ( "id" );

        //Then
        verify ( this.repository ).findById ( "id" );
        //And
        assertEquals ( expected, result );
    }

    // getById : Caso de teste 2: id errado deve lançar erro
    @Test
    void getByIdShouldThrowErrorWhenIdDoesNotExist() {
        //Given
        Consulta expected = new Consulta (
                "medico_id",
                "paciente_id",
                "dor de barriga",
                "atestado_id"
        );
        when ( this.repository.findById ( "id_certo" ) ).thenReturn ( Optional.of ( expected ) );

        //When -> Then
        Throwable throwable =
                assertThrows (
                        EntityNotFoundException.class,
                        () ->  this.underTest.getById ( "id_errado" )
                );
        assertEquals ( Consulta.class.getName () + " não encontrado.", throwable.getMessage () );
    }

    // add : Caso de teste 1: objeto deve ser passado corretamente para o repositório
    @Test
    void addShouldPassObjectToRepository() {
        //Given
        Consulta expected = new Consulta (
                "medico_id",
                "paciente_id",
                "dor de barriga",
                "atestado_id"
        );

        //When
        this.underTest.add ( expected );

        //Then
        verify ( this.repository ).save ( expected );

    }
    @Test
    void delete() {
        when(this.repository.findById ( "consulta_id" ))
                .thenReturn ( Optional.of ( new Consulta (
                        "medico_id",
                        "paciente_id",
                        "dor de barriga",
                        "atestado_id"
                ) ) );
        //When
        this.underTest.delete ( "consulta_id" );
        //Then
        verify ( this.repository ).delete ( "consulta_id" );
    }

    @Test
    void update() {
        //Given:  recebe um id, e novos dados
        Consulta att = new Consulta (
                "medico_id",
                "paciente_id",
                "dor de barriga",
                "atestado_id"
        );
        Consulta atual = new Consulta (
                "medico_id",
                "paciente_id",
                "dor de barriga",
                "atestado_id"
        );
        when ( this.repository.findById ( "consulta_id") ).thenReturn ( Optional.of ( atual ) );

        //When: deve chamar repo.save com os novos dados
        this.underTest.update ( "consulta_id", att );

        //Then: o obj deve ter seus dados atualizados e ter upDatedAt
        verify ( this.repository ).save ( atual );
        assertNotNull (atual.getUpdated_at ());
    }
}