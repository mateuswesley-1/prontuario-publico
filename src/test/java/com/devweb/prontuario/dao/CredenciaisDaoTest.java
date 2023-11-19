package com.devweb.prontuario.dao;

import com.devweb.prontuario.entities.Credenciais;
import com.devweb.prontuario.repositories.jbdc.CredenciaisRepositoryJbdc;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CredenciaisDaoTest {

    private CredenciaisDao underTest;
    @Mock
    private CredenciaisRepositoryJbdc repository;
    @Mock
    private BCryptPasswordEncoder encoder;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        this.autoCloseable = MockitoAnnotations.openMocks ( this );
        this.underTest = new CredenciaisDao ( this.repository);
    }
    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close ();
    }

    // findAll: Não deve ser possível usar findAll
    @Test
    void findAllShouldThrowException() {

        //Given
        Pageable pageable = PageRequest.of ( 1, 10 );
        when(this.repository.findAll ( pageable )).thenThrow ( UnsupportedOperationException.class );

        //When -> Then
        assertThrows (
                UnsupportedOperationException.class,
                () -> underTest.findAll ( pageable )
        );

    }
    // findById : should throw exception
    @Test
    void findByIdShouldThrowException() {
        //Given
        when ( this.repository.findById ( "credenciais_id" ) ).thenThrow ( UnsupportedOperationException.class );

        //when -> then
        assertThrows (
                UnsupportedOperationException.class,
                () -> underTest.findById ( "credenciais_id" )
        );
    }
    @Test
    void addShouldPassObjectToRepository() {
        //Given
        Credenciais expected = new Credenciais ();
        expected.setUsername ( "mateus_wesley_medeiros" );
        expected.setPassword ( "mateus" );
        when( this.encoder.encode ( expected.getPassword () )).thenReturn ( "senha_criptografada" );

        Credenciais result = new Credenciais ();
        result.setUsername ( expected.getUsername () );
        result.setPassword ( expected.getPassword ( ) );
        result.setId ( UUID.randomUUID ().toString () );

        when( this.repository.save ( expected )).thenReturn ( result );

        //When
        Credenciais serviceResult = this.underTest.save ( expected );

        //Then
        verify ( this.repository ).save ( expected );
        assertEquals ( result, serviceResult );

    }
    @Test
    void deleteShouldThrowException() {
        //Given
        when(this.repository.findById ( "credenciais_id" )).thenReturn ( Optional.of ( new Credenciais () ) );
        //When
        this.underTest.delete ( "credenciais_id" );
        //Then
        verify ( this.repository ).delete ( "credenciais_id" );
    }

}