package com.devweb.prontuario.dto.prescricao;

import com.devweb.prontuario.base.EntityResponseDTO;
import com.devweb.prontuario.dto.medicamento.MedicamentoResponseDTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class PrescricaoResponseDTO extends EntityResponseDTO {
    private int qtdDias;
    private int frequenciaHoras;
    private MedicamentoResponseDTO medicamento;
    private String id;
    private String consultaId;
    private LocalDateTime deletedAt;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
