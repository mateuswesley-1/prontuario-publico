package com.devweb.prontuario.services;

import com.devweb.prontuario.BaseService;
import com.devweb.prontuario.repositories.ConsultaRepository;
import org.springframework.stereotype.Service;

import com.devweb.prontuario.entities.Consulta;

@Service
public class ConsultaService extends BaseService<Consulta, ConsultaRepository> {

    protected ConsultaService(ConsultaRepository repository) {
        super(repository, Consulta.class);
    }

    @Override
    public void patch(Consulta novo, Consulta atual) {
        if(novo.getAnamnese() != null) atual.setAnamnese(novo.getAnamnese());
        if(novo.getAtestado_id () != null) atual.setAtestado_id (novo.getAtestado_id ());
        if(novo.getMedico_id () != null) atual.setMedico_id (novo.getMedico_id ());
        if(novo.getPaciente_id () != null) atual.setPaciente_id (novo.getPaciente_id ());
    }
    
}
