package com.devweb.prontuario.dto.Funcionario;

import java.time.LocalDate;

import com.devweb.prontuario.base.EntityResponseDTO;

import lombok.*;



@Getter
@Setter
public class FuncionarioResponseDTO extends EntityResponseDTO {
    private String CPF;
    private String nome;
    private LocalDate dataNascimento;
    private String endereco;
    private String email;
    private String cargo;
}
