package com.devweb.prontuario.dto.prescricao;

import com.devweb.prontuario.EntityRequestDTO;

import lombok.Data;


@Data
public class PrescricaoRequestDTO extends EntityRequestDTO {
    private int qtdDias;
    private int frequenciaHoras;
    private String idMedicamento;
}
