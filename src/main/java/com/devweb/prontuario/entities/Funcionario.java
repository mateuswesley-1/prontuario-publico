package com.devweb.prontuario.entities;

import java.time.LocalDate;

import com.devweb.prontuario.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Getter
@Setter
@SQLDelete(sql = "UPDATE funcionario_tb SET deleted_at = CURRENT_TIMESTAMP WHERE id=?")
@Where(clause = "deleted_at is null")
@Builder
public class Funcionario extends BaseEntity {

    @NotBlank(message = "O campo cpf não pode estar em branco.")
    public String cpf;
    
    @NotBlank(message = "O campo nome não pode estar em branco.")
    private String nome;

    @NotNull(message = "O campo dataNascimento não pode estar em branco.")
    private LocalDate data_nascimento;

    @NotBlank(message = "O campo endereço não pode estar em branco.")
    private String endereco;

    @NotBlank(message = "O campo email não pode estar em branco.")
    @Email
    private String email;   

    @NotBlank(message = "O campo cargo não pode estar em branco.")
    private String cargo;

    public void setDataNascimento(LocalDate time){
        this.data_nascimento = time;
    }

    public LocalDate getDataNascimento(){
        return this.data_nascimento;
    }
}
