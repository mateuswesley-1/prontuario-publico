package com.devweb.prontuario.dao;

import com.devweb.prontuario.dao.interfaces.DataAcessObject;
import com.devweb.prontuario.entities.Funcionario;
import com.devweb.prontuario.exceptions.CustomExceptions.EntityNotFoundException;
import com.devweb.prontuario.repositories.jpa.FuncionarioRepositoryJpa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class FuncionarioDao implements DataAcessObject<Funcionario> {

    private final FuncionarioRepositoryJpa repository;

    public FuncionarioDao(FuncionarioRepositoryJpa atestadoRepository) {
        repository = atestadoRepository;
    }

    @Override
    public Optional<Funcionario> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Page<Funcionario> findAll(Pageable pageable) {
        return repository.findAll ( pageable );
    }

    @Override
    public void delete(String id) {
        repository.deleteById ( id );
    }

    @Override
    public Funcionario update(Funcionario entityInstance) {
        Optional<Funcionario> busca = repository.findById(entityInstance.getId());
        if (busca.isPresent()){
            return repository.save(entityInstance);
        }else{
            throw new EntityNotFoundException ("Not found");
        }
    }

    @Override
    public Funcionario create(Funcionario entityInstance) {
        return repository.save ( entityInstance );
    }
}
