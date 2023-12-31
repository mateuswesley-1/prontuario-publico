package com.devweb.prontuario.dto.atestado;

import com.devweb.prontuario.base.EntityResponseDTO;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AtestadoResponseDTO extends EntityResponseDTO{
    private String id;
    private int duracao;
    private String descricao;
}
