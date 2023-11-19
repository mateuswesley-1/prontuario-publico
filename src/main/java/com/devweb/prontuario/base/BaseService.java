package com.devweb.prontuario.base;

import java.time.LocalDateTime;
import java.util.Optional;


import com.devweb.prontuario.dao.interfaces.DataAcessObject;
import com.devweb.prontuario.exceptions.CustomExceptions.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.devweb.prontuario.messages.Mensagens;

public abstract class BaseService<T extends BaseEntity, Dao extends DataAcessObject<T>> {

    protected final Dao dao;
    protected final Class<T> entityType;

    protected BaseService(Dao dao, Class<T> entityType){
        this.dao = dao;
        this.entityType = entityType;
    }

    public Page<T> getAll(Pageable pageable){
        return dao.findAll(pageable);
    }

    public T getById(String id){
        Optional<T> resultado = dao.findById(id);
        if (resultado.isPresent()) return resultado.get(); 
        throw new EntityNotFoundException (Mensagens.NOT_FOUND.formatar(entityType.getName()));
    }

    public T create(T entityInstance){
        entityInstance.setCreatedAt (LocalDateTime.now());
        entityInstance.setUpdatedAt (LocalDateTime.now());
        return dao.create (entityInstance);
    }

    public void delete(String id){
        Optional<T> result = dao.findById(id);
        if (result.isPresent()){
            dao.delete(id);
        } else {
            throw new EntityNotFoundException(Mensagens.NOT_FOUND.formatar(entityType.getName()));
        }

    }
    
    public void update(String id, T newInstance) {
        Optional<T> response = dao.findById(id);
        if(response.isPresent()) {
            response.get().setUpdatedAt (LocalDateTime.now());
            T actualInstance = response.get();
            this.patch(newInstance, actualInstance);
            dao.update (actualInstance);
        } else {
            throw new EntityNotFoundException(Mensagens.NOT_FOUND.formatar(entityType.getName()));
        }
    }

    public void patch(T newInstance, T actualInstance){

    }
}
