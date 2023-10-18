package com.devweb.prontuario.dto.medicamento;

import com.devweb.prontuario.EntityRequestDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MedicamentoRequestDTO extends EntityRequestDTO {
    private String nome;
    private double dose;
}
