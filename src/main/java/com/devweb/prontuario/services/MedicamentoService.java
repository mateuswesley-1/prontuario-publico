package com.devweb.prontuario.services;

import com.devweb.prontuario.repositories.MedicamentoRepository;
import org.springframework.stereotype.Service;

import com.devweb.prontuario.entities.Medicamento;

@Service
public class MedicamentoService extends BaseService<Medicamento, MedicamentoRepository>{

    protected MedicamentoService(MedicamentoRepository repository) {
        super(repository, Medicamento.class);
    }

    @Override
    void patch(Medicamento novo, Medicamento atual) {
        if(novo.getDose() != 0) atual.setDose(novo.getDose());
        if(novo.getNome() != null) atual.setNome(novo.getNome());
    }
    
}
