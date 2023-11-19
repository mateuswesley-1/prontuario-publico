package com.devweb.prontuario.repositories.jpa;

import com.devweb.prontuario.entities.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MedicamentoRepositoryJpa extends JpaRepository<Medicamento, String> {
}
