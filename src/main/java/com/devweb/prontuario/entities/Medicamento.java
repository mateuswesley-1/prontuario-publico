package com.devweb.prontuario.entities;


import com.devweb.prontuario.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;

import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Component
@Entity
@Table(name="medicamento_tb")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE medicamento_tb SET deleted_at = CURRENT_TIMESTAMP WHERE id=?")
@Where(clause = "deleted_at is null")
public class Medicamento extends BaseEntity {

    @NotBlank(message = "O campo nome não pode estar em branco.")
    public String nome;

    @NotBlank(message = "O campo dose não pode estar em branco.")
    public float dose;

}
