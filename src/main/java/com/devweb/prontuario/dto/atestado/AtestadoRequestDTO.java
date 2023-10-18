package com.devweb.prontuario.dto.atestado;

import com.devweb.prontuario.EntityRequestDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class AtestadoRequestDTO extends EntityRequestDTO {
    // nao precisa de id pq o id ta vindo no path
    private int duracao;
    private String descricao;
}
