package com.devweb.prontuario.entities;

import com.devweb.prontuario.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="prescricao_tb")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Prescricao extends BaseEntity {

    @Min(1)
    @Max(200)
    private int qtdDias;

    @Min(1)
    @Max(24)
    private int frequenciaHoras;

    @NotNull
    @ManyToOne
    private Medicamento medicamento;


}




