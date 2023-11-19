package com.devweb.prontuario.entities;

import java.time.LocalDate;

import com.devweb.prontuario.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Component
@Entity
@Table(name = "funcionario_tb")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Funcionario extends BaseEntity {

    @CPF
    @NotNull
    public String cpf;

    @Size(min = 5, max = 50)
    @NotNull
    private String nome;

    @PastOrPresent
    @NotNull
    private LocalDate dataNascimento;

    @Size(min = 5, max = 250)
    @NotNull
    private String endereco;

    @Email
    @NotNull
    private String email;

    @Size(min = 5, max = 250)
    @NotNull
    private String cargo;

}
