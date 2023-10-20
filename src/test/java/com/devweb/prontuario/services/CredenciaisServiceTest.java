package com.devweb.prontuario.services;

import com.devweb.prontuario.entities.Credenciais;
import com.devweb.prontuario.repositories.CredenciaisRepository;

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

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CredenciaisServiceTest {

    private CredenciaisService underTest;
    @Mock
    private CredenciaisRepository repository;
    @Mock
    private BCryptPasswordEncoder encoder;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        this.autoCloseable = MockitoAnnotations.openMocks ( this );
        this.underTest = new CredenciaisService ( this.repository, this.encoder );
    }
    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close ();
    }

    // getAll: Não deve ser possível usar getAll
    @Test
    void getAllShouldThrowException() {

        //Given
        Pageable pageable = PageRequest.of ( 1, 10 );
        when(this.repository.findAll ( pageable )).thenThrow ( UnsupportedOperationException.class );

        //When -> Then
        assertThrows (
                UnsupportedOperationException.class,
                () -> underTest.getAll ( pageable )
        );

    }
    // getById : should throw exception
    @Test
    void getByIdShouldThrowException() {
        //Given
        when ( this.repository.findById ( "credenciais_id" ) ).thenThrow ( UnsupportedOperationException.class );

        //when -> then
        assertThrows (
                UnsupportedOperationException.class,
                () -> underTest.getById ( "credenciais_id" )
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

        when( this.repository.save ( expected )).thenReturn ( Optional.of(result) );

        //When
        Credenciais serviceResult = this.underTest.create ( expected );

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