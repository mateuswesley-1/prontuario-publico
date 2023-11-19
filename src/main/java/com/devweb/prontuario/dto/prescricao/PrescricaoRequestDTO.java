package com.devweb.prontuario.dto.prescricao;

import com.devweb.prontuario.base.EntityRequestDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class PrescricaoRequestDTO extends EntityRequestDTO {
    private int qtdDias;
    private int frequenciaHoras;
    private String idMedicamento;
    private String idConsulta;
}
