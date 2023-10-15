package com.devweb.prontuario.dto.atestado;

import com.devweb.prontuario.controllers.AtestadoController;
import com.devweb.prontuario.EntityResponseDTO;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AtestadoResponseDTO extends EntityResponseDTO{
    private String id;
    private int duracao;
    private String descricao;
}
