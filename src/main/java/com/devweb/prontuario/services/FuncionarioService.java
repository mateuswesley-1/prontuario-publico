package com.devweb.prontuario.services;

import com.devweb.prontuario.base.BaseService;
import com.devweb.prontuario.dao.FuncionarioDao;
import org.springframework.stereotype.Service;

import com.devweb.prontuario.entities.Funcionario;

@Service
public class FuncionarioService extends BaseService<Funcionario, FuncionarioDao> {
    public FuncionarioService(FuncionarioDao repository) {
        super(repository, Funcionario.class);
    }

    @Override
    public void patch(Funcionario novo, Funcionario atual) {
        if(novo.getCpf() != null) atual.setCpf(novo.getCpf());
        if(novo.getCargo() != null) atual.setCargo(novo.getCargo());
        if(novo.getDataNascimento() != null) atual.setDataNascimento(novo.getDataNascimento());
        if(novo.getEmail() != null)  atual.setEmail(novo.getEmail());
        if(novo.getEndereco() != null)  atual.setEndereco(novo.getEndereco());
        if(novo.getNome() != null)  atual.setNome(novo.getNome());
    }
}
