package com.devweb.prontuario.dto.Funcionario;

import java.time.LocalDate;

import com.devweb.prontuario.EntityRequestDto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioRequestDTO extends EntityRequestDto {

    private String CPF;
    
    private String nome;

    private LocalDate dataNascimento;

    private String endereco;

    private String email;   

    private String cargo;

}
