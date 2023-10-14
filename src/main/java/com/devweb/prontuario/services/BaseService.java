package com.devweb.prontuario.services;

import java.time.LocalDateTime;
import java.util.Optional;

import com.devweb.prontuario.BaseRepositoryJBDC;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.devweb.prontuario.BaseEntity;
import com.devweb.prontuario.messages.Mensagens;

import jakarta.persistence.EntityNotFoundException;


public class BaseService<T extends BaseEntity, Repo extends BaseRepositoryJBDC<T>> {

    protected final Repo repository;
    protected final Class<T> entityType;

    protected BaseService(Repo repository, Class<T> entityType){
        this.repository = repository;
        this.entityType = entityType;
    }

    public Page<T> getAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public T getById(String id){
        Optional<T> resultado = repository.findById(id);
        if (resultado.isPresent()) return resultado.get(); 
        throw new EntityNotFoundException(Mensagens.NOT_FOUND.formatar(entityType.getName()));
    }

    public void add(T entityInstance){
        entityInstance.setCreatedAt(LocalDateTime.now());
        entityInstance.setUpdatedAt(LocalDateTime.now());
        repository.save(entityInstance);
    }

    public void delete(String id){
        Optional<T> result = repository.findById(id);
        if (result.isPresent()){
            repository.delete(id);
        } else {
            throw new EntityNotFoundException(Mensagens.NOT_FOUND.formatar(entityType.getName()));
        }

    }
    
    public void update(String id, T newInstance) {
        Optional<T> response = repository.findById(id);
        if(response.isPresent()) {
            response.get().setUpdatedAt(LocalDateTime.now());
            T actualInstance = response.get();
            this.patch(newInstance, actualInstance);
            repository.save(actualInstance);
        }
        throw new EntityNotFoundException(Mensagens.NOT_FOUND.formatar(entityType.getName()));
    }

    void patch(T newInstance, T actualInstance){

    };
}
