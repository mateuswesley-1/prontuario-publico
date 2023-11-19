package com.devweb.prontuario.services;

import com.devweb.prontuario.base.BaseService;
import com.devweb.prontuario.dao.ConsultaDao;
import org.springframework.stereotype.Service;

import com.devweb.prontuario.entities.Consulta;

@Service
public class ConsultaService extends BaseService<Consulta, ConsultaDao> {

    protected ConsultaService(ConsultaDao dao) {
        super(dao, Consulta.class);
    }

    @Override
    public void patch(Consulta novo, Consulta atual) {
        if(novo.getAnamnese() != null) atual.setAnamnese(novo.getAnamnese());
        if(novo.getAtestado () != null) atual.setAtestado (novo.getAtestado ());
        if(novo.getMedicoId () != null) atual.setMedicoId (novo.getMedicoId ());
        if(novo.getPacienteId () != null) atual.setPacienteId (novo.getPacienteId ());
    }
    
}
