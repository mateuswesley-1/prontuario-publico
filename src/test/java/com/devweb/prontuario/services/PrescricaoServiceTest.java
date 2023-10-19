package com.devweb.prontuario.services;

import com.devweb.prontuario.entities.Prescricao;
import com.devweb.prontuario.exceptions.CustomExceptions.EntityNotFoundException;
import com.devweb.prontuario.repositories.PrescricaoRepository;
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

class PrescricaoServiceTest {
    private PrescricaoService underTest;
    @Mock
    private PrescricaoRepository repository;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        this.autoCloseable = MockitoAnnotations.openMocks ( this );
        this.underTest = new PrescricaoService ( this.repository );
    }
    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close ();
    }

    // getAll : Caso de teste 1: Tem dados, deve retornar esses dados.
    @Test
    void getAllShouldReturnRepositoryResultWithoutChanges() {
        Prescricao prescricao = new Prescricao (
                10,
                3,
                "medicamento_id",
                "consulta_id"
        );
        Page<Prescricao> expected = new PageImpl<> ( List.of ( prescricao ) );
        //Given
        Pageable pageable = PageRequest.of ( 1, 10 );
        when ( this.repository.findAll ( pageable ) ).thenReturn ( expected );

        //When
        Page<Prescricao> result = underTest.getAll ( pageable );

        //Then: Verifica se está chamando o método certo do repo
        verify(this.repository).findAll ( pageable );

        // And: O resultado retornado pelo service é o mesmo do repo.
        assertEquals ( expected, result);
    }
    // getAll : Caso de teste 2: não tem dados, deve retornar array vazia.
    @Test
    void getAllShouldReturnEmptyArrayWhenRepoDontReturnNothing() {
        //Given
        Page<Prescricao> expected = new PageImpl<> ( List.of (  ) );
        Pageable pageable = PageRequest.of ( 1, 10 );
        when ( this.repository.findAll ( pageable ) ).thenReturn ( expected );

        //When
        Page<Prescricao> result = underTest.getAll ( pageable );

        //Then: Verifica se está chamando o método certo do repo
        verify(this.repository).findAll ( pageable );

        // And: O resultado retornado pelo service é o mesmo do repo.
        assertEquals ( expected, result);
    }
    // getById : Caso de teste 1: id correto deve retornar dado correto
    @Test
    void getByIdShouldReturnRepositoryResultWithoutChanges() {
        //Given
        Prescricao expected = new Prescricao (
                10,
                3,
                "medicamento_id",
                "consulta_id"
        );
        when ( this.repository.findById ( "id" ) ).thenReturn ( Optional.of ( expected ) );

        //When
        Prescricao result = this.underTest.getById ( "id" );

        //Then
        verify ( this.repository ).findById ( "id" );
        //And
        assertEquals ( expected, result );
    }

    // getById : Caso de teste 2: id errado deve lançar erro
    @Test
    void getByIdShouldThrowErrorWhenIdDoesNotExist() {
        //Given
        Prescricao expected = new Prescricao (
                10,
                3,
                "medicamento_id",
                "consulta_id"
        );
        when ( this.repository.findById ( "id_certo" ) ).thenReturn ( Optional.of ( expected ) );

        //When -> Then
        Throwable throwable =
                assertThrows (
                        EntityNotFoundException.class,
                        () ->  this.underTest.getById ( "id_errado" )
                );
        assertEquals ( Prescricao.class.getName () + " não encontrado.", throwable.getMessage () );
    }

    // add : Caso de teste 1: objeto deve ser passado corretamente para o repositório
    @Test
    void addShouldPassObjectToRepository() {
        //Given
        Prescricao expected = new Prescricao (
                10,
                3,
                "medicamento_id",
                "consulta_id"
        );

        //When
        this.underTest.create ( expected );

        //Then
        verify ( this.repository ).save ( expected );

    }
    @Test
    void delete() {
        when(this.repository.findById ( "prescricao_id" ))
                .thenReturn ( Optional.of ( new Prescricao (
                        10,
                        3,
                        "medicamento_id",
                        "consulta_id"
                ) ) );
        //When
        this.underTest.delete ( "prescricao_id" );
        //Then
        verify ( this.repository ).delete ( "prescricao_id" );
    }

    @Test
    void update() {
        //Given:  recebe um id, e novos dados
        Prescricao att = new Prescricao (
                10,
                3,
                "medicamento_id",
                "consulta_id"
        );
        Prescricao atual = new Prescricao (
                10,
                3,
                "medicamento_id",
                "consulta_id"
        );
        when ( this.repository.findById ( "prescricao_id") ).thenReturn ( Optional.of ( atual ) );

        //When: deve chamar repo.save com os novos dados
        this.underTest.update ( "prescricao_id", att );

        //Then: o obj deve ter seus dados atualizados e ter upDatedAt
        verify ( this.repository ).save ( atual );
        assertNotNull (atual.getUpdated_at ());
    }
}