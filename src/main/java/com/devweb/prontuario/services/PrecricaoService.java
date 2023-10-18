package com.devweb.prontuario.services;

import com.devweb.prontuario.repositories.PrescricaoRepository;
import org.springframework.stereotype.Service;

import com.devweb.prontuario.entities.Prescricao;

@Service
public class PrecricaoService extends BaseService<Prescricao, PrescricaoRepository>{

    protected PrecricaoService(PrescricaoRepository repository) {
        super(repository, Prescricao.class);
    }

    @Override
    void patch(Prescricao novo, Prescricao atual) {
        if(novo.getFrequenciaHoras() != 0) atual.setFrequenciaHoras(novo.getFrequenciaHoras());
        if(novo.getQtdDias() != 0) atual.setQtdDias(novo.getQtdDias());
    }
    
}
