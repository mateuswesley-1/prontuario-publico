package com.devweb.prontuario.services;

import com.devweb.prontuario.base.BaseService;
import com.devweb.prontuario.dao.MedicamentoDao;
import org.springframework.stereotype.Service;

import com.devweb.prontuario.entities.Medicamento;

@Service
public class MedicamentoService extends BaseService<Medicamento, MedicamentoDao> {

    protected MedicamentoService(MedicamentoDao dao) {
        super(dao, Medicamento.class);
    }

    @Override
    public void patch(Medicamento novo, Medicamento atual) {
        if(novo.getDose() != 0) atual.setDose(novo.getDose());
        if(novo.getNome() != null) atual.setNome(novo.getNome());
    }
    
}
