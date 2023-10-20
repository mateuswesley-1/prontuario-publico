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

@Builder
public class Funcionario extends BaseEntity {
    @Getter
    @Setter
    public String cpf;
    @Getter
    @Setter
    private String nome;
    private LocalDate data_nascimento;
    @Getter
    @Setter
    private String endereco;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String cargo;

    public void setDataNascimento(LocalDate time){
        this.data_nascimento = time;
    }

    public LocalDate getDataNascimento(){
        return this.data_nascimento;
    }
}
