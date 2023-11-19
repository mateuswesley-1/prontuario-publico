package com.devweb.prontuario.dao.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface DataAcessObject<T> {
    Optional<T> findById(String id);

    Page<T> findAll(Pageable pageable);

    T update(T entityInstance);
    T create(T entityInstance);
    void delete(String id);
}
