package com.devweb.prontuario.dao;

import com.devweb.prontuario.dao.interfaces.DataAcessObject;
import com.devweb.prontuario.entities.Medico;
import com.devweb.prontuario.exceptions.CustomExceptions.EntityNotFoundException;
import com.devweb.prontuario.repositories.jpa.MedicoRepositoryJpa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class MedicoDao implements DataAcessObject<Medico> {

    private final MedicoRepositoryJpa repository;

    public MedicoDao(MedicoRepositoryJpa atestadoRepository) {
        repository = atestadoRepository;
    }

    @Override
    public Optional<Medico> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Page<Medico> findAll(Pageable pageable) {
        return repository.findAll ( pageable );
    }
    
    @Override
    public void delete(String id) {
        repository.deleteById ( id );
    }

    public Medico update(Medico entityInstance) {
        Optional<Medico> busca = repository.findById(entityInstance.getId());
        if (busca.isPresent()){
            return repository.save(entityInstance);
        }else{
            throw new EntityNotFoundException ("Not found");
        }
    }

    @Override
    public Medico create(Medico entityInstance) {
        return repository.save ( entityInstance );
    }
}
