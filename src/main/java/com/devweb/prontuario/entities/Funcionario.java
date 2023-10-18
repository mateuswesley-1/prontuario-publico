package com.devweb.prontuario.entities;

import java.time.LocalDate;

import com.devweb.prontuario.BaseEntity;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Funcionario extends BaseEntity {

    public String cpf;
    private String nome;
    private LocalDate data_nascimento;
    private String endereco;
    private String email;
    private String cargo;

    public void setDataNascimento(LocalDate time){
        this.data_nascimento = time;
    }

    public LocalDate getDataNascimento(){
        return this.data_nascimento;
    }
}
