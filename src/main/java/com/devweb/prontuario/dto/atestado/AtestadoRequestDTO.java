package com.devweb.prontuario.dto.atestado;

import com.devweb.prontuario.dto.EntityRequestDto;

import lombok.Data;


@Data
public class AtestadoRequestDTO extends EntityRequestDto{
    private String id;
    private int duracao;
    private String descricao;
}
