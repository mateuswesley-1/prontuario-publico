package com.devweb.prontuario.dao;

import com.devweb.prontuario.dao.interfaces.DataAcessObject;
import com.devweb.prontuario.entities.Consulta;
import com.devweb.prontuario.exceptions.CustomExceptions.EntityNotFoundException;
import com.devweb.prontuario.repositories.jpa.ConsultaRepositoryJpa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class ConsultaDao implements DataAcessObject<Consulta> {

    private final ConsultaRepositoryJpa repository;

    public ConsultaDao(ConsultaRepositoryJpa atestadoRepository) {
        repository = atestadoRepository;
    }

    @Override
    public Optional<Consulta> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Page<Consulta> findAll(Pageable pageable) {
        return repository.findAll ( pageable );
    }


    @Override
    public Consulta update(Consulta entityInstance) {
        Optional<Consulta> busca = repository.findById(entityInstance.getId());
        if (busca.isPresent()){
            return repository.save(entityInstance);
        }else{
            throw new EntityNotFoundException ("Not found");
        }
    }

    @Override
    public Consulta create(Consulta entityInstance) {
        return repository.save ( entityInstance );
    }

    @Override
    public void delete(String id) {
        repository.deleteById ( id );
    }
}
