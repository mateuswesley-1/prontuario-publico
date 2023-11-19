package com.devweb.prontuario.services;

import com.devweb.prontuario.dao.AtestadoDao;
import com.devweb.prontuario.entities.Atestado;
import com.devweb.prontuario.exceptions.CustomExceptions.EntityNotFoundException;
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
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class AtestadoServiceTest {
    private AtestadoService underTest;
    @Mock
    private AtestadoDao dao;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        this.autoCloseable = MockitoAnnotations.openMocks ( this );
        this.underTest = new AtestadoService ( this.dao );
    }
    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close ();
    }

    // getAll : Caso de teste 1: Tem dados, deve retornar esses dados.
    @Test
    void getAllShouldReturnRepositoryResultWithoutChanges() {
        Atestado atestado = new Atestado ( 10, "atestado teste" );
        Page<Atestado> expected = new PageImpl<> ( List.of ( atestado ) );
        //Given
        Pageable pageable = PageRequest.of ( 1, 10 );
        when ( this.dao.findAll ( pageable ) ).thenReturn ( expected );

        //When
        Page<Atestado> result = underTest.getAll ( pageable );

        //Then: Verifica se está chamando o método certo do repo
        verify(this.dao).findAll ( pageable );

        // And: O resultado retornado pelo service é o mesmo do repo.
        assertEquals ( expected, result);
    }
    // getAll : Caso de teste 2: não tem dados, deve retornar array vazia.
    @Test
    void getAllShouldReturnEmptyArrayWhenRepoDontReturnNothing() {
        //Given
        Page<Atestado> expected = new PageImpl<> ( List.of (  ) );
        Pageable pageable = PageRequest.of ( 1, 10 );
        when ( this.dao.findAll ( pageable ) ).thenReturn ( expected );

        //When
        Page<Atestado> result = underTest.getAll ( pageable );

        //Then: Verifica se está chamando o método certo do repo
        verify(this.dao).findAll ( pageable );

        // And: O resultado retornado pelo service é o mesmo do repo.
        assertEquals ( expected, result);
    }
    // getById : Caso de teste 1: id correto deve retornar dado correto
    @Test
    void getByIdShouldReturnRepositoryResultWithoutChanges() {
        //Given
        Atestado expected = new Atestado ( 10, "atestado teste" );
        when ( this.dao.findById ( "id" ) ).thenReturn ( Optional.of ( expected ) );

        //When
        Atestado result = this.underTest.getById ( "id" );

        //Then
        verify ( this.dao ).findById ( "id" );
        //And
        assertEquals ( expected, result );
    }

    // getById : Caso de teste 2: id errado deve lançar erro
    @Test
    void getByIdShouldThrowErrorWhenIdDoesNotExist() {
        //Given
        Atestado expected = new Atestado ( 10, "atestado teste" );
        when ( this.dao.findById ( "id_certo" ) ).thenReturn ( Optional.of ( expected ) );

        //When -> Then
        Throwable throwable =
                assertThrows (
                        EntityNotFoundException.class,
                        () ->  this.underTest.getById ( "id_errado" )
                );
        assertEquals ( Atestado.class.getName () + " não encontrado.", throwable.getMessage () );
    }

    // add : Caso de teste 1: objeto deve ser passado corretamente para o repositório
    @Test
    void addShouldPassObjectToRepository() {
        //Given
        Atestado expected = new Atestado ( 10, "atestado teste" );


        Atestado result = new Atestado (
                expected.getDuracao (),
                expected.getDescricao ()
        );

        result.setId ( UUID.randomUUID ().toString () );
        when( this.dao.create ( expected )).thenReturn ( result );

        //When
        Atestado serviceResult = this.underTest.create ( expected );

        //Then
        verify ( this.dao ).create ( expected );
        assertEquals ( result, serviceResult );

    }
    @Test
    void delete() {
        when(this.dao.findById ( "atestadoId" ))
                .thenReturn ( Optional.of ( new Atestado ( 10, "teste" ) ) );
        //When
        this.underTest.delete ( "atestadoId" );
        //Then
        verify ( this.dao ).delete ( "atestadoId" );
    }

    @Test
    void update() {
        //Given:  recebe um id, e novos dados
        Atestado att = new Atestado ( 5, "novo atestado teste" );
        Atestado atual = new Atestado ( 10, "atestado teste" );
        when ( this.dao.findById ( "atestadoId") ).thenReturn ( Optional.of ( atual ) );

        //When: deve chamar repo.save com os novos dados
        this.underTest.update ( "atestadoId", att );

        //Then: o obj deve ter seus dados atualizados e ter upDatedAt
        verify ( this.dao ).update ( atual );
        assertNotNull (atual.getUpdatedAt ());
    }

}