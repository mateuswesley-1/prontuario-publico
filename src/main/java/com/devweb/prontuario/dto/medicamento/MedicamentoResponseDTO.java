package com.devweb.prontuario.dto.medicamento;

import com.devweb.prontuario.controllers.MedicamentoController;
import com.devweb.prontuario.EntityResponseDTO;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
public class MedicamentoResponseDTO extends EntityResponseDTO {
    private String nome;
    private double dose;
}
