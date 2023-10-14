package com.devweb.prontuario.dto.atestado;

import com.devweb.prontuario.controllers.AtestadoController;
import com.devweb.prontuario.EntityResponseDTO;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AtestadoResponseDTO extends EntityResponseDTO<AtestadoController> {

    public AtestadoResponseDTO(){
        super(AtestadoController.class);
    }
    private int duracao;
    private String descricao;
}
