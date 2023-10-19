package com.devweb.prontuario.services;

import com.devweb.prontuario.BaseService;
import org.springframework.stereotype.Service;

import com.devweb.prontuario.entities.Atestado;
import com.devweb.prontuario.repositories.AtestadoRepository;

@Service
public class AtestadoService extends BaseService<Atestado, AtestadoRepository> {

    protected AtestadoService(AtestadoRepository repository) {
        super(repository, Atestado.class);
    }

    public void patch(Atestado novo, Atestado atual) {
        if(novo.getDuracao() != 0) atual.setDuracao(novo.getDuracao());
        if(novo.getDescricao() != null) atual.setDescricao(novo.getDescricao());
    }
}
