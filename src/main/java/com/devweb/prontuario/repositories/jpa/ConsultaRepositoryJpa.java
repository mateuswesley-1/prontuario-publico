package com.devweb.prontuario.repositories.jpa;

import com.devweb.prontuario.entities.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ConsultaRepositoryJpa extends JpaRepository<Consulta, String> {
}
