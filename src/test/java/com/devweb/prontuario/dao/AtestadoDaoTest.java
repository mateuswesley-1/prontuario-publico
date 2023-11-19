//package com.devweb.prontuario.dao;
//
//
//import com.devweb.prontuario.entities.Atestado;
//import com.devweb.prontuario.entities.Atestado;
//import com.devweb.prontuario.exceptions.CustomExceptions.EntityNotFoundException;
//import com.devweb.prontuario.repositories.jpa.AtestadoRepository;
//
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
//
//class AtestadoDaoTest {
//    private AtestadoDao underTest;
//    @Mock
//    private AtestadoRepository repository;
//    private AutoCloseable autoCloseable;
//
//    @BeforeEach
//    void setUp() {
//        this.autoCloseable = MockitoAnnotations.openMocks ( this );
//        this.underTest = new AtestadoDao ( this.repository );
//    }
//    @AfterEach
//    void tearDown() throws Exception{
//        autoCloseable.close ();
//    }
//
//    // findAll : Caso de teste 1: Tem dados, deve retornar esses dados.
//    @Test
//    void findAllShouldReturnRepositoryResultWithoutChanges() {
//        Atestado atestado = new Atestado ( 10, "atestado teste" );
//        Page<Atestado> expected = new PageImpl<> ( List.of ( atestado ) );
//        //Given
//        Pageable pageable = PageRequest.of ( 1, 10 );
//        when ( this.repository.findAll ( pageable ) ).thenReturn ( expected );
//
//        //When
//        Page<Atestado> result = underTest.findAll ( pageable );
//
//        //Then: Verifica se está chamando o método certo do repo
//        verify(this.repository).findAll ( pageable );
//
//        // And: O resultado retornado pelo service é o mesmo do repo.
//        assertEquals ( expected, result);
//    }
//    // findAll : Caso de teste 2: não tem dados, deve retornar array vazia.
//    @Test
//    void findAllShouldReturnEmptyArrayWhenRepoDontReturnNothing() {
//        //Given
//        Page<Atestado> expected = new PageImpl<> ( List.of (  ) );
//        Pageable pageable = PageRequest.of ( 1, 10 );
//        when ( this.repository.findAll ( pageable ) ).thenReturn ( expected );
//
//        //When
//        Page<Atestado> result = underTest.findAll ( pageable );
//
//        //Then: Verifica se está chamando o método certo do repo
//        verify(this.repository).findAll ( pageable );
//
//        // And: O resultado retornado pelo service é o mesmo do repo.
//        assertEquals ( expected, result);
//    }
//    // findById : Caso de teste 1: id correto deve retornar dado correto
//    @Test
//    void findByIdShouldReturnRepositoryResultWithoutChanges() {
//        //Given
//        Atestado expected = new Atestado ( 10, "atestado teste" );
//        when ( this.repository.findById ( "id" ) ).thenReturn ( Optional.of ( expected ) );
//
//        //When
//        Optional<Atestado> result = this.underTest.findById ( "id" );
//
//        //Then
//        verify ( this.repository ).findById ( "id" );
//        //And
//        assert(result).isPresent ();
//    }
//
//    // findById : Caso de teste 2: id errado deve lançar erro
//    @Test
//    void findByIdShouldThrowErrorWhenIdDoesNotExist() {
//        //Given
//        Atestado expected = new Atestado ( 10, "atestado teste" );
//        when ( this.repository.findById ( "id_certo" ) ).thenReturn ( Optional.of ( expected ) );
//
//        //When -> Then
//        Throwable throwable =
//                assertThrows (
//                        EntityNotFoundException.class,
//                        () ->  this.underTest.findById ( "id_errado" )
//                );
//        assertEquals ( Atestado.class.getName () + " não encontrado.", throwable.getMessage () );
//    }
//
//    // add : Caso de teste 1: objeto deve ser passado corretamente para o repositório
//    @Test
//    void addShouldPassObjectToRepository() {
//        //Given
//        Atestado expected = new Atestado ( 10, "atestado teste" );
//
//
//        Atestado result = new Atestado (
//                expected.getDuracao (),
//                expected.getDescricao ()
//        );
//
//        result.setId ( UUID.randomUUID ().toString () );
//        when( this.repository.save ( expected )).thenReturn ( result );
//
//        //When
//        Atestado serviceResult = this.underTest.create ( expected );
//
//        //Then
//        verify ( this.repository ).save ( expected );
//        assertEquals ( result, serviceResult );
//
//    }
//    @Test
//    void delete() {
//        when(this.repository.findById ( "atestadoId" ))
//                .thenReturn ( Optional.of ( new Atestado ( 10, "teste" ) ) );
//        //When
//        this.underTest.delete ( "atestadoId" );
//        //Then
//        verify ( this.repository ).deleteById ( "atestadoId" );
//    }
//
//    @Test
//    void update() {
//        //Given
//        Atestado att = new Atestado (
//        );
//        att.setId ( "medicamentoId" );
//
//        Atestado atual = new Atestado (
//                "diazepan",
//                50.0f
//        );
//
//        atual.setId ( "medicamentoId" );
//
//
//        when(this.repository.findById ( "medicamentoId" ))
//                .thenReturn ( Optional.of ( atual ) );
//
//        //When: deve chamar repo.save com os novos dados
//        this.underTest.update ( att );
//
//        //Then: o obj deve ter seus dados atualizados e ter upDatedAt
//        verify ( this.repository ).save ( att );
//    }
//
//    @Test
//    void findByIdShouldBeEmptyWhenIdWrong() {
//        // When/Then
//        Optional<Atestado> medicamento = underTest.findById ( "id_errado" );
//
//        // then
//        assert(medicamento).isEmpty ();
//    }
//
//}