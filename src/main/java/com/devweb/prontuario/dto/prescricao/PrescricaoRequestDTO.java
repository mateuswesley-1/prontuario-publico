package com.devweb.prontuario.dto.prescricao;

import com.devweb.prontuario.dto.EntityRequestDto;

import lombok.Data;


@Data
public class PrescricaoRequestDTO extends EntityRequestDto{
    private int qtdDias;
    private int frequenciaHoras;
    private String idMedicamento;
}
