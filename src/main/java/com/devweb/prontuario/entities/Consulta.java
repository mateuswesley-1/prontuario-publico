package com.devweb.prontuario.entities;

import java.util.ArrayList;
import java.util.List;

import com.devweb.prontuario.BaseEntity;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Entity
@Table(name = "consulta_tb")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SQLDelete(sql = "UPDATE consulta_tb SET deleted_at = CURRENT_TIMESTAMP WHERE id=?")
@Where(clause = "deleted_at is null")
public class Consulta extends BaseEntity {


    @NotBlank(message = "O campo médico não pode estar em branco.")
    private String medico_id;

    @NotNull(message = "O campo paciente não pode estar em branco.")
    private String paciente_id;

    @NotBlank(message = "O campo anamnese não pode estar em branco.")
    private String anamnese;

    @Cascade(CascadeType.PERSIST)
    private String atestado_id;

}
