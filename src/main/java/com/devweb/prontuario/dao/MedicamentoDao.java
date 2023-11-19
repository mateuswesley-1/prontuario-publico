package com.devweb.prontuario.dao;

import com.devweb.prontuario.dao.interfaces.DataAcessObject;
import com.devweb.prontuario.entities.Medicamento;
import com.devweb.prontuario.exceptions.CustomExceptions.EntityNotFoundException;
import com.devweb.prontuario.repositories.jpa.MedicamentoRepositoryJpa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class MedicamentoDao implements DataAcessObject<Medicamento> {

    private final MedicamentoRepositoryJpa repository;

    public MedicamentoDao(MedicamentoRepositoryJpa atestadoRepository) {
        repository = atestadoRepository;
    }

    @Override
    public Optional<Medicamento> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Page<Medicamento> findAll(Pageable pageable) {
        return repository.findAll ( pageable );
    }

    @Override
    public void delete(String id) {
        repository.deleteById ( id );
    }

    @Override
    public Medicamento update(Medicamento entityInstance) {
        Optional<Medicamento> busca = repository.findById(entityInstance.getId());
        if (busca.isPresent()){
            return repository.save(entityInstance);
        }else{
            throw new EntityNotFoundException ("Not found");
        }
    }

    @Override
    public Medicamento create(Medicamento entityInstance) {
        return repository.save ( entityInstance );
    }
}
