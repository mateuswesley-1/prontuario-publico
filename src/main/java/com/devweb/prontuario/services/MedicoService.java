package com.devweb.prontuario.services;

import org.springframework.stereotype.Service;

import com.devweb.prontuario.entities.Medico;
import com.devweb.prontuario.repositories.MedicoRepository;

@Service
public class MedicoService extends BaseService<Medico, MedicoRepository>{

    private final FuncionarioService service;

    protected MedicoService(MedicoRepository repository, FuncionarioService service) {
        super(repository, Medico.class);
        this.service = service;
    }

    @Override
    void patch(Medico novo, Medico atual) {
        if(novo.getFuncionario_id () != null) atual.setFuncionario_id (novo.getFuncionario_id ());
        if(novo.getCrm() != 0) atual.setCrm(novo.getCrm());
        if(novo.getEspecialidade() != null) atual.setEspecialidade(novo.getEspecialidade());
    }
    
}
