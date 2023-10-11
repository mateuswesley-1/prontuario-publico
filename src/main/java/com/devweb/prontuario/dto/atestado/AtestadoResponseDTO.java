package com.devweb.prontuario.dto.atestado;

import com.devweb.prontuario.controllers.AtestadoController;
import com.devweb.prontuario.dto.EntityResponseDTO;

import com.devweb.prontuario.entities.Atestado;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
