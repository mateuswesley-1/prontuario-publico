package com.devweb.prontuario.dto.atestado;

import com.devweb.prontuario.EntityRequestDto;

import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class AtestadoRequestDTO extends EntityRequestDto{
    private String id;
    private int duracao;
    private String descricao;
}
