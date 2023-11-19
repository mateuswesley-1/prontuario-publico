package com.devweb.prontuario.services;

import com.devweb.prontuario.dao.CredenciaisDao;
import com.devweb.prontuario.entities.Credenciais;

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
    private CredenciaisDao dao;
    @Mock
    private BCryptPasswordEncoder encoder;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        this.autoCloseable = MockitoAnnotations.openMocks ( this );
        this.underTest = new CredenciaisService ( this.dao, this.encoder );
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
        when(this.dao.findAll ( pageable )).thenThrow ( UnsupportedOperationException.class );

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
        when ( this.dao.findById ( "credenciais_id" ) ).thenThrow ( UnsupportedOperationException.class );

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

        when( this.dao.save ( expected )).thenReturn ( result );

        //When
        Credenciais serviceResult = this.underTest.create ( expected );

        //Then
        verify ( this.dao ).save ( expected );
        assertEquals ( result, serviceResult );

    }
    @Test
    void deleteShouldThrowException() {
        //Given
        when(this.dao.findById ( "credenciais_id" )).thenReturn ( Optional.of ( new Credenciais () ) );
        //When
        this.underTest.delete ( "credenciais_id" );
        //Then
        verify ( this.dao ).delete ( "credenciais_id" );
    }

}