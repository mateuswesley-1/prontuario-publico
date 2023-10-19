package com.devweb.prontuario;

import java.time.LocalDateTime;
import java.util.Optional;


import com.devweb.prontuario.exceptions.CustomExceptions.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.devweb.prontuario.messages.Mensagens;

public abstract class BaseService<T extends BaseEntity, Repo extends BaseRepositoryJBDC<T>> {

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
        throw new EntityNotFoundException (Mensagens.NOT_FOUND.formatar(entityType.getName()));
    }

    public T create(T entityInstance){
        entityInstance.setCreated_at (LocalDateTime.now());
        entityInstance.setUpdated_at (LocalDateTime.now());
        Optional<T> result = repository.save(entityInstance);

        if(result.isPresent ()) return result.get ();
        throw new RuntimeException ( "Nao foi possivel criar o objeto fornecido" );
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
            response.get().setUpdated_at(LocalDateTime.now());
            T actualInstance = response.get();
            this.patch(newInstance, actualInstance);
            repository.save(actualInstance);
        } else {
            throw new EntityNotFoundException(Mensagens.NOT_FOUND.formatar(entityType.getName()));
        }
    }

    public void patch(T newInstance, T actualInstance){

    }
}
