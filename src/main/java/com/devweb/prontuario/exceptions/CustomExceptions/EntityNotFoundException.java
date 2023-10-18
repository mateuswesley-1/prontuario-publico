package com.devweb.prontuario.exceptions.CustomExceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String mensagem){
        super(mensagem);
    }
}
