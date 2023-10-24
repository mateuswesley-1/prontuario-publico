package com.devweb.prontuario.entities;

import com.devweb.prontuario.BaseEntity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class Prescricao extends BaseEntity {
    @Min(1)
    @Max(200)
    private int qtd_dias;
    @Min(1)
    @Max(24)
    private int frequencia_horas;
    @Setter
    @Getter
    @NotNull
    @Pattern(
            regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$",
            flags = Pattern.Flag.CASE_INSENSITIVE
    )
    private String medicamento_id;
    @Setter
    @Getter
    @NotNull
    @Pattern(
            regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$",
            flags = Pattern.Flag.CASE_INSENSITIVE
    )
    private String consulta_id;

    public int getFrequenciaHoras() {
        return frequencia_horas;
    }

    public void setFrequenciaHoras(int frequencia_horas) {
        this.frequencia_horas = frequencia_horas;
    }

    public int getQtdDias() {
        return qtd_dias;
    }

    public void setQtdDias(int qtd_dias) {
        this.qtd_dias = qtd_dias;
    }
}
