package com.devweb.prontuario.dto.prescricao;

import com.devweb.prontuario.controllers.PrescricaoController;
import com.devweb.prontuario.EntityResponseDTO;
import com.devweb.prontuario.dto.medicamento.MedicamentoResponseDTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class PrescricaoResponseDTO extends EntityResponseDTO<PrescricaoController> {
    public PrescricaoResponseDTO(){
        super(PrescricaoController.class);
    }

    private int qtdDias;
    private int frequenciaHoras;
    private MedicamentoResponseDTO medicamento;
    private String id;
    private LocalDateTime deletedAt;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
