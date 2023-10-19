package com.devweb.prontuario.services;

import com.devweb.prontuario.BaseService;
import com.devweb.prontuario.repositories.PrescricaoRepository;
import org.springframework.stereotype.Service;

import com.devweb.prontuario.entities.Prescricao;

@Service
public class PrescricaoService extends BaseService<Prescricao, PrescricaoRepository> {

    protected PrescricaoService(PrescricaoRepository repository) {
        super(repository, Prescricao.class);
    }

    @Override
    public void patch(Prescricao novo, Prescricao atual) {
        if(novo.getFrequenciaHoras() != 0) atual.setFrequenciaHoras(novo.getFrequenciaHoras());
        if(novo.getQtdDias() != 0) atual.setQtdDias(novo.getQtdDias());
    }
    
}
