package com.devweb.prontuario.dao;

import com.devweb.prontuario.entities.Medicamento;
import com.devweb.prontuario.repositories.jpa.MedicamentoRepositoryJpa;
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
class DaoTest {
    private MedicamentoDao underTest;
    @Mock
    private MedicamentoRepositoryJpa repository;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        this.autoCloseable = MockitoAnnotations.openMocks ( this );
        this.underTest = new MedicamentoDao ( this.repository );
    }
    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close ();
    }

    // findAll : Caso de teste 1: Tem dados, deve retornar esses dados.
    @Test
    void findAllShouldReturnRepositoryResultWithoutChanges() {
        Medicamento medicamento = new Medicamento (
                "diazepan",
                50.0f
        );
        Page<Medicamento> expected = new PageImpl<> ( List.of ( medicamento ) );
        //Given
        Pageable pageable = PageRequest.of ( 1, 10 );
        when ( this.repository.findAll ( pageable ) ).thenReturn ( expected );

        //When
        Page<Medicamento> result = underTest.findAll ( pageable );

        //Then: Verifica se está chamando o método certo do repo
        verify(this.repository).findAll ( pageable );

        // And: O resultado retornado pelo service é o mesmo do repo.
        assertEquals ( expected, result);
    }
    // findAll : Caso de teste 2: não tem dados, deve retornar array vazia.
    @Test
    void findAllShouldReturnEmptyArrayWhenRepoDontReturnNothing() {
        //Given
        Page<Medicamento> expected = new PageImpl<> ( List.of (  ) );
        Pageable pageable = PageRequest.of ( 1, 10 );
        when ( this.repository.findAll ( pageable ) ).thenReturn ( expected );

        //When
        Page<Medicamento> result = underTest.findAll ( pageable );

        //Then: Verifica se está chamando o método certo do repo
        verify(this.repository).findAll ( pageable );

        // And: O resultado retornado pelo service é o mesmo do repo.
        assertEquals ( expected, result);
    }
    // findById : Caso de teste 1: id correto deve retornar dado correto
    @Test
    void findByIdShouldReturnRepositoryResultWithoutChanges() {
        //Given
        Medicamento expected = new Medicamento (
                "diazepan",
                50.0f
        );
        when ( this.repository.findById ( "id" ) ).thenReturn ( Optional.of ( expected ) );

        //When
        Optional<Medicamento> result = this.underTest.findById ( "id" );

        //Then
        verify ( this.repository ).findById ( "id" );
        //And
        assert(result).isPresent ();
        //And
        assertEquals ( expected, result.get () );
    }

    // findById : Caso de teste 2: id errado deve lançar erro


    // add : Caso de teste 1: objeto deve ser passado corretamente para o repositório
    @Test
    void addShouldPassObjectToRepository() {
        //Given
        Medicamento expected = new Medicamento (
                "diazepan",
                50.0f
        );


        Medicamento result = new Medicamento (
                expected.getNome (),
                expected.getDose ()
        );

        result.setId ( UUID.randomUUID ().toString () );
        when( this.repository.save ( expected )).thenReturn ( result );

        //When
        Medicamento serviceResult = this.underTest.create ( expected );

        //Then
        verify ( this.repository ).save ( expected );
        assertEquals ( result, serviceResult );
    }
    @Test
    void delete() {
        when(this.repository.findById ( "medicamentoId" ))
                .thenReturn ( Optional.of ( new Medicamento (
                        "diazepan",
                        50.0f
                ) ) );
        //When
        this.underTest.delete ( "medicamentoId" );
        //Then
        verify ( this.repository ).deleteById ( "medicamentoId" );
    }

    @Test
    void update() {
        //Given
        Medicamento att = new Medicamento (
                "diazepan",
                50.0f
        );
        att.setId ( "medicamentoId" );

        Medicamento atual = new Medicamento (
                "diazepan",
                50.0f
        );

        atual.setId ( "medicamentoId" );


        when(this.repository.findById ( "medicamentoId" ))
                .thenReturn ( Optional.of ( atual ) );

        //When: deve chamar repo.save com os novos dados
        this.underTest.update ( att );

        //Then: o obj deve ter seus dados atualizados e ter upDatedAt
        verify ( this.repository ).save ( att );
    }

    @Test
    void findByIdShouldBeEmptyWhenIdWrong() {
        // When/Then
        Optional<Medicamento> medicamento = underTest.findById ( "id_errado" );

        // then
        assert(medicamento).isEmpty ();
    }
}