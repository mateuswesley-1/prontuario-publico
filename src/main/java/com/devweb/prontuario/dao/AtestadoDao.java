package com.devweb.prontuario.dao;

import com.devweb.prontuario.dao.interfaces.DataAcessObject;
import com.devweb.prontuario.entities.Atestado;
import com.devweb.prontuario.exceptions.CustomExceptions.EntityNotFoundException;
import com.devweb.prontuario.repositories.jpa.AtestadoRepositoryJpa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AtestadoDao implements DataAcessObject<Atestado> {

    private final AtestadoRepositoryJpa repository;

    public AtestadoDao(AtestadoRepositoryJpa atestadoRepositoryJpa) {
        repository = atestadoRepositoryJpa;
    }

    @Override
    public Optional<Atestado> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Page<Atestado> findAll(Pageable pageable) {
        return repository.findAll ( pageable );
    }


    @Override
    public Atestado update(Atestado entityInstance) {
        Optional<Atestado> busca = repository.findById(entityInstance.getId());
        if (busca.isPresent()){
            return repository.save(entityInstance);
        }else{
            throw new EntityNotFoundException ("Not found");
        }
    }

    @Override
    public Atestado create(Atestado entityInstance) {
        return repository.save ( entityInstance );
    }

    @Override
    public void delete(String id) {
        repository.deleteById ( id );
    }
}
