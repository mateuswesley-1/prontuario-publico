package com.devweb.prontuario.services;

import com.devweb.prontuario.base.BaseService;
import com.devweb.prontuario.dao.AtestadoDao;
import org.springframework.stereotype.Service;

import com.devweb.prontuario.entities.Atestado;

@Service
public class AtestadoService extends BaseService<Atestado, AtestadoDao> {

    public AtestadoService(AtestadoDao dao) {
        super(dao, Atestado.class);
    }

    public void patch(Atestado novo, Atestado atual) {
        if(novo.getDuracao() != 0) atual.setDuracao(novo.getDuracao());
        if(novo.getDescricao() != null) atual.setDescricao(novo.getDescricao());
    }
}
