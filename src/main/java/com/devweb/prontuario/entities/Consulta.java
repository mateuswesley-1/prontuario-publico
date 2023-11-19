package com.devweb.prontuario.entities;


import com.devweb.prontuario.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Entity
@Table(name = "consulta_tb")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Consulta extends BaseEntity {
    @NotNull
    @Pattern(
            regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$",
            flags = Pattern.Flag.CASE_INSENSITIVE
    )
    private String medicoId;

    @NotNull
    @Pattern(
            regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$",
            flags = Pattern.Flag.CASE_INSENSITIVE
    )
    private String pacienteId;
    @NotNull
    @Size(min = 1, max = 9999)
    private String anamnese;

    @NotNull
    @OneToOne(cascade=CascadeType.ALL)
    private Atestado Atestado;


    @OneToMany
    @JoinColumn(name = "consulta_id")
    private List<Prescricao> prescricoes;
}
