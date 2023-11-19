//package com.devweb.prontuario.dao;
//
//import com.devweb.prontuario.entities.Prescricao;
//import com.devweb.prontuario.exceptions.CustomExceptions.EntityNotFoundException;
//import com.devweb.prontuario.repositories.jpa.PrescricaoRepository;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//class PrescricaoDaoTest {
//    private PrescricaoDao underTest;
//    @Mock
//    private PrescricaoRepository repository;
//    private AutoCloseable autoCloseable;
//
//    @BeforeEach
//    void setUp() {
//        this.autoCloseable = MockitoAnnotations.openMocks ( this );
//        this.underTest = new PrescricaoDao ( this.repository );
//    }
//    @AfterEach
//    void tearDown() throws Exception{
//        autoCloseable.close ();
//    }
//
//    // findAll : Caso de teste 1: Tem dados, deve retornar esses dados.
//    @Test
//    void findAllShouldReturnRepositoryResultWithoutChanges() {
//        Prescricao prescricao = new Prescricao (
//                10,
//                3,
//                "medicamentoId",
//                "consultaId"
//        );
//        Page<Prescricao> expected = new PageImpl<> ( List.of ( prescricao ) );
//        //Given
//        Pageable pageable = PageRequest.of ( 1, 10 );
//        when ( this.repository.findAll ( pageable ) ).thenReturn ( expected );
//
//        //When
//        Page<Prescricao> result = underTest.findAll ( pageable );
//
//        //Then: Verifica se está chamando o método certo do repo
//        verify(this.repository ).findAll ( pageable );
//
//        // And: O resultado retornado pelo service é o mesmo do repo.
//        assertEquals ( expected, result);
//    }
//    // findAll : Caso de teste 2: não tem dados, deve retornar array vazia.
//    @Test
//    void findAllShouldReturnEmptyArrayWhenRepoDontReturnNothing() {
//        //Given
//        Page<Prescricao> expected = new PageImpl<> ( List.of (  ) );
//        Pageable pageable = PageRequest.of ( 1, 10 );
//        when ( this.repository.findAll ( pageable ) ).thenReturn ( expected );
//
//        //When
//        Page<Prescricao> result = underTest.findAll ( pageable );
//
//        //Then: Verifica se está chamando o método certo do repo
//        verify(this.repository ).findAll ( pageable );
//
//        // And: O resultado retornado pelo service é o mesmo do repo.
//        assertEquals ( expected, result);
//    }
//    // findById : Caso de teste 1: id correto deve retornar dado correto
//    @Test
//    void findByIdShouldReturnRepositoryResultWithoutChanges() {
//        //Given
//        Prescricao expected = new Prescricao (
//                10,
//                3,
//                "medicamentoId",
//                "consultaId"
//        );
//        when ( this.repository.findById ( "id" ) ).thenReturn ( Optional.of ( expected ) );
//
//        //When
//        Optional<Prescricao> result = this.underTest.findById ( "id" );
//
//        //Then
//        verify ( this.repository ).findById ( "id" );
//        //And
//        assert(result).isPresent ();
//        //And
//        assertEquals ( expected, result.get () );
//    }
//
//    // findById : Caso de teste 2: id errado deve lançar erro
//    @Test
//    void findByIdShouldThrowErrorWhenIdDoesNotExist() {
//        //Given
//        Prescricao expected = new Prescricao (
//                10,
//                3,
//                "medicamentoId",
//                "consultaId"
//        );
//        when ( this.repository.findById ( "id_certo" ) ).thenReturn ( Optional.of ( expected ) );
//
//        //When -> Then
//        Throwable throwable =
//                assertThrows (
//                        EntityNotFoundException.class,
//                        () ->  this.underTest.findById ( "id_errado" )
//                );
//        assertEquals ( Prescricao.class.getName () + " não encontrado.", throwable.getMessage () );
//    }
//
//    // add : Caso de teste 1: objeto deve ser passado corretamente para o repositório
//    @Test
//    void addShouldPassObjectToRepositoryAndReturnObjWithId() {
//        //Given
//        Prescricao expected = new Prescricao (
//                10,
//                3,
//                "medicamentoId",
//                "consultaId"
//        );
//
//        Prescricao result = new Prescricao (
//                expected.getQtdDias (),
//                expected.getFrequenciaHoras (),
//                expected.getMedicamentoId (),
//                expected.getConsultaId ()
//        );
//        result.setId ( UUID.randomUUID ().toString () );
//        when( this.repository.save ( expected )).thenReturn ( result );
//        //When
//        Prescricao serviceResult = this.underTest.create ( expected );
//
//        //Then
//        verify ( this.repository ).save ( expected );
//        assertEquals ( result, serviceResult );
//    }
//    @Test
//    void delete() {
//        when(this.repository.findById ( "prescricao_id" ))
//                .thenReturn ( Optional.of ( new Prescricao (
//                        10,
//                        3,
//                        "medicamentoId",
//                        "consultaId"
//                ) ) );
//        //When
//        this.underTest.delete ( "prescricao_id" );
//        //Then
//        verify ( this.repository ).deleteById ( "prescricao_id" );
//    }
//
//    @Test
//    void update() {
//        //Given:  recebe um id, e novos dados
//        Prescricao att = new Prescricao (
//                10,
//                3,
//                "medicamentoId",
//                "consultaId"
//        );
//        Prescricao atual = new Prescricao (
//                10,
//                3,
//                "medicamentoId",
//                "consultaId"
//        );
//        when ( this.repository.findById ( "prescricao_id") ).thenReturn ( Optional.of ( atual ) );
//
//        //When: deve chamar repo.save com os novos dados
//        this.underTest.update ( att );
//
//        //Then: o obj deve ter seus dados atualizados e ter upDatedAt
//        verify ( this.repository ).save ( atual );
//        assertNotNull (atual.getUpdatedAt ());
//    }
//}