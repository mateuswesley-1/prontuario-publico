package com.devweb.prontuario.entities;

import com.devweb.prontuario.BaseEntity;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class Prescricao extends BaseEntity {
    private int qtd_dias;
    private int frequencia_horas;

    @Setter
    @Getter
    private String medicamento_id;

    @Setter
    @Getter
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
