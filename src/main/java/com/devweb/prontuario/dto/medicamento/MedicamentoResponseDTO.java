package com.devweb.prontuario.dto.medicamento;

import com.devweb.prontuario.base.EntityResponseDTO;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
public class MedicamentoResponseDTO extends EntityResponseDTO {
    private String nome;
    private double dose;
}
