package com.devweb.prontuario.dao;

import com.devweb.prontuario.dao.interfaces.CredenciaisDaoInterface;
import com.devweb.prontuario.entities.Credenciais;
import com.devweb.prontuario.exceptions.CustomExceptions.EntityNotFoundException;
import com.devweb.prontuario.repositories.jbdc.CredenciaisRepositoryJbdc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class CredenciaisDao implements CredenciaisDaoInterface {

    private final CredenciaisRepositoryJbdc repository;

    public CredenciaisDao(CredenciaisRepositoryJbdc atestadoRepository) {
        repository = atestadoRepository;
    }

    @Override
    public Optional<Credenciais> findByUsername(String username) {
        return repository.findByUsername ( username );
    }

    @Override
    public Optional<Credenciais> findById(String id) {
        throw new UnsupportedOperationException (  );
    }

    @Override
    public Page<Credenciais> findAll(Pageable pageable) {
        throw new UnsupportedOperationException (  );
    }

    @Override
    public Credenciais save(Credenciais entityInstance) {
        return repository.save ( entityInstance );
    }

    @Override
    public void delete(String id) {
        this.repository.delete ( id );
    }

    @Override
    public Credenciais update(Credenciais entityInstance) {
        Optional<Credenciais> busca = repository.findById(entityInstance.getId());
        if (busca.isPresent()){
            return repository.save(entityInstance);
        }else{
            throw new EntityNotFoundException ("Not found");
        }
    }

    @Override
    public Credenciais create(Credenciais entityInstance) {
        return repository.save ( entityInstance );
    }

}
