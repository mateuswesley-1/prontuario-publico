package com.devweb.prontuario.dto.medicamento;

import com.devweb.prontuario.controllers.MedicamentoController;
import com.devweb.prontuario.dto.EntityResponseDTO;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
public class MedicamentoResponseDTO extends EntityResponseDTO<MedicamentoController> {

    public MedicamentoResponseDTO(){
        super(MedicamentoController.class);
    }

    private String nome;
    private double dose;
}
