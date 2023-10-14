package com.devweb.prontuario.dto.medicamento;

import com.devweb.prontuario.EntityRequestDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MedicamentoRequestDTO extends EntityRequestDto{
    private String nome;
    private double dose;
}
