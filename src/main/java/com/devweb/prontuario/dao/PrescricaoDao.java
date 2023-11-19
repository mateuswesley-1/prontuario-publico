package com.devweb.prontuario.dao;

import com.devweb.prontuario.dao.interfaces.DataAcessObject;
import com.devweb.prontuario.entities.Prescricao;
import com.devweb.prontuario.exceptions.CustomExceptions.EntityNotFoundException;
import com.devweb.prontuario.repositories.jpa.PrescricaoRepositoryJpa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class PrescricaoDao implements DataAcessObject<Prescricao> {

    private final PrescricaoRepositoryJpa repository;

    public PrescricaoDao(PrescricaoRepositoryJpa atestadoRepository) {
        repository = atestadoRepository;
    }

    @Override
    public Optional<Prescricao> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Page<Prescricao> findAll(Pageable pageable) {
        return repository.findAll ( pageable );
    }
    
    @Override
    public void delete(String id) {
        repository.deleteById ( id );
    }

    public Prescricao update(Prescricao entityInstance) {
        Optional<Prescricao> busca = repository.findById(entityInstance.getId());
        if (busca.isPresent()){
            return repository.save(entityInstance);
        }else{
            throw new EntityNotFoundException ("Not found");
        }
    }

    @Override
    public Prescricao create(Prescricao entityInstance) {
        return repository.save ( entityInstance );
    }
}
