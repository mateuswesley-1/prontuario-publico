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
        service.patch(novo.getFuncionario(), atual.getFuncionario());
        if(novo.getCrm() != 0) atual.setCrm(novo.getCrm());
        if(novo.getEspecialidade() != null) atual.setEspecialidade(novo.getEspecialidade());
    }
    
}
