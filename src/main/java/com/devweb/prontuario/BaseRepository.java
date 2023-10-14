package com.devweb.prontuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, String>, PagingAndSortingRepository<T, String>{
    
}
