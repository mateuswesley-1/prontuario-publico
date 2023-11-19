package com.devweb.prontuario.repositories.jpa;

import com.devweb.prontuario.entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FuncionarioRepositoryJpa extends JpaRepository<Funcionario, String> {
}
