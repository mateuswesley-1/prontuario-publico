package com.devweb.prontuario.messages;

import lombok.Getter;
import lombok.Setter;



public enum Mensagens{
    NOT_FOUND("%s não encontrado."),
    ALREADY_EXISTS("Esse %s já existe no sistema.");

    @Getter
    @Setter
    private String templace;

    private Mensagens(String template){
        this.templace = template;
    }

    public String formatar(String nomeTipo){
        return String.format(this.templace, nomeTipo);
    }
}



