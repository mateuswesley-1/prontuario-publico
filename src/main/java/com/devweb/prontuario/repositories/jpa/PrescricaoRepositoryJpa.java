package com.devweb.prontuario.repositories.jpa;

import com.devweb.prontuario.entities.Prescricao;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PrescricaoRepositoryJpa extends JpaRepository<Prescricao, String> {
}
