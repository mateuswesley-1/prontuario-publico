package com.devweb.prontuario.dto.Funcionario;

import java.time.LocalDate;

import com.devweb.prontuario.controllers.FuncionarioController;
import com.devweb.prontuario.EntityResponseDTO;

import lombok.*;



@Getter
@Setter
public class FuncionarioResponseDTO extends EntityResponseDTO<FuncionarioController> {

    public FuncionarioResponseDTO(){
        super(FuncionarioController.class);
    }

    private String CPF;
    private String nome;
    private LocalDate dataNascimento;
    private String endereco;
    private String email;
    private String cargo;

}
