package com.devweb.prontuario.entities;

import com.devweb.prontuario.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Component
@Entity
@Table(name="prescricao_tb")
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE prescricao_tb SET deleted_at = CURRENT_TIMESTAMP WHERE id=?")
@Where(clause = "deleted_at is null")
public class Precricao extends BaseEntity {
    @Min(1)
    @Max(60)
    private int qtd_dias;

    @Min(1)
    @Max(48)
    private int frequencia_horas;

    @ManyToOne  
    @JoinColumn(name = "medicamento_id")
    @Setter
    @Getter
    private Medicamento medicamento;

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
