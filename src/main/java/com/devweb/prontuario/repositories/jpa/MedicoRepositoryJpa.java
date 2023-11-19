package com.devweb.prontuario.repositories.jpa;

import com.devweb.prontuario.entities.Medico;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MedicoRepositoryJpa extends JpaRepository<Medico, String> {
}
