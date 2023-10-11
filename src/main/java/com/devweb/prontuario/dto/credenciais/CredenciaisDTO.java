package com.devweb.prontuario.dto.credenciais;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CredenciaisDTO {

    private String username;
    private String password;
    private String cargo;
    private String nome;
    private String CPF;
    private LocalDate dataNascimento;
    private String endereco;
    private String email;   

}
