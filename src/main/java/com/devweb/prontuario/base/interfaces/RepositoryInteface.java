package com.devweb.prontuario.base.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

 interface RepositoryInteface<T> {
     Optional<T> findById(String id);

     Page<T> findAll(Pageable pageable);

     Optional<T> save(T entityInstance);

     void delete(String id);
}
