package com.devweb.prontuario.entities;

import java.time.LocalDate;

import com.devweb.prontuario.BaseEntity;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;
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
    @CPF
    @NotNull
    public String cpf;
    @Getter
    @Setter
    @Size(min = 5, max = 50)
    @NotNull
    private String nome;
    @PastOrPresent
    @NotNull
    private LocalDate data_nascimento;
    @Getter
    @Setter
    @Size(min = 5, max = 250)
    @NotNull
    private String endereco;
    @Getter
    @Setter
    @Email
    @NotNull
    private String email;
    @Getter
    @Setter
    @Size(min = 5, max = 250)
    @NotNull
    private String cargo;

    public void setDataNascimento(LocalDate time){
        this.data_nascimento = time;
    }

    public LocalDate getDataNascimento(){
        return this.data_nascimento;
    }
}
