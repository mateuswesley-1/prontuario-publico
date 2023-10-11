package com.devweb.prontuario.services;

import com.devweb.prontuario.repositories.ConsultaRepository;
import org.springframework.stereotype.Service;

import com.devweb.prontuario.entities.Consulta;

@Service
public class ConsultaService extends BaseService<Consulta, ConsultaRepository> {

    protected ConsultaService(ConsultaRepository repository) {
        super(repository, Consulta.class);
    }

    @Override
    void patch(Consulta novo, Consulta atual) {
        if(novo.getAnamnese() != null) atual.setAnamnese(novo.getAnamnese());
        if(novo.getAtestado() != null) atual.setAtestado(novo.getAtestado());
        if(novo.getMedico() != null) atual.setMedico(novo.getMedico());
        if(novo.getPaciente() != null) atual.setPaciente(novo.getPaciente());
        if(novo.getPrescricao() != null) atual.setPrescricao(novo.getPrescricao());
    }
    
}
