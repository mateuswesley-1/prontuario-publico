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
        Credenciais credenciais = new Credenciais ();
        credenciais.setUsername ( "mateus_wesley_medeiros" );
        credenciais.setPassword ( "mateus" );
        when( this.encoder.encode ( credenciais.getPassword () )).thenReturn ( "senha_criptografada" );


        //When
        this.underTest.add ( credenciais );

        // Then
        verify ( this.encoder ).encode ( "mateus" );
        verify ( this.repository ).save ( credenciais );



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