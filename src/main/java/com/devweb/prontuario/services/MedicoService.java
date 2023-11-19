package com.devweb.prontuario.services;

import com.devweb.prontuario.base.BaseService;
import com.devweb.prontuario.dao.MedicoDao;
import org.springframework.stereotype.Service;

import com.devweb.prontuario.entities.Medico;

@Service
public class MedicoService extends BaseService<Medico, MedicoDao> {


    protected MedicoService(MedicoDao dao) {
        super(dao, Medico.class);
    }

    @Override
    public void patch(Medico novo, Medico atual) {
        if(novo.getFuncionarioId () != null) atual.setFuncionarioId (novo.getFuncionarioId ());
        if(novo.getCrm() != 0) atual.setCrm(novo.getCrm());
        if(novo.getEspecialidade() != null) atual.setEspecialidade(novo.getEspecialidade());
    }
    
}
