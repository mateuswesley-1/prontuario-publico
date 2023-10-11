package com.devweb.prontuario.dto.Funcionario;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.devweb.prontuario.controllers.BaseController;
import com.devweb.prontuario.controllers.FuncionarioController;
import com.devweb.prontuario.dto.EntityResponseDTO;

import com.devweb.prontuario.entities.Funcionario;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
