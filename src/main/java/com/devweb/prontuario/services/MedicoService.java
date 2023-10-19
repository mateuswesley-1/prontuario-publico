package com.devweb.prontuario.services;

import com.devweb.prontuario.BaseService;
import org.springframework.stereotype.Service;

import com.devweb.prontuario.entities.Medico;
import com.devweb.prontuario.repositories.MedicoRepository;

@Service
public class MedicoService extends BaseService<Medico, MedicoRepository> {


    protected MedicoService(MedicoRepository repository) {
        super(repository, Medico.class);
    }

    @Override
    public void patch(Medico novo, Medico atual) {
        if(novo.getFuncionario_id () != null) atual.setFuncionario_id (novo.getFuncionario_id ());
        if(novo.getCrm() != 0) atual.setCrm(novo.getCrm());
        if(novo.getEspecialidade() != null) atual.setEspecialidade(novo.getEspecialidade());
    }
    
}
