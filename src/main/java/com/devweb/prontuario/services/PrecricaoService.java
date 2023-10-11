package com.devweb.prontuario.services;

import com.devweb.prontuario.repositories.PrecricaoRepository;
import org.springframework.stereotype.Service;

import com.devweb.prontuario.entities.Precricao;

@Service
public class PrecricaoService extends BaseService<Precricao, PrecricaoRepository>{

    protected PrecricaoService(PrecricaoRepository repository) {
        super(repository, Precricao.class);
    }

    @Override
    void patch(Precricao novo, Precricao atual) {
        if(novo.getFrequenciaHoras() != 0) atual.setFrequenciaHoras(novo.getFrequenciaHoras());
        if(novo.getQtdDias() != 0) atual.setQtdDias(novo.getQtdDias());
    }
    
}
