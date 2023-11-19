package com.devweb.prontuario.services;

import com.devweb.prontuario.base.BaseService;
import com.devweb.prontuario.dao.PrescricaoDao;
import org.springframework.stereotype.Service;

import com.devweb.prontuario.entities.Prescricao;

@Service
public class PrescricaoService extends BaseService<Prescricao, PrescricaoDao> {

    protected PrescricaoService(PrescricaoDao dao) {
        super(dao, Prescricao.class);
    }

    @Override
    public void patch(Prescricao novo, Prescricao atual) {
        if(novo.getFrequenciaHoras() != 0) atual.setFrequenciaHoras(novo.getFrequenciaHoras());
        if(novo.getQtdDias() != 0) atual.setQtdDias(novo.getQtdDias());
    }
    
}
