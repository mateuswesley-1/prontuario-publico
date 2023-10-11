package com.devweb.prontuario.repositories;

import com.devweb.prontuario.entities.Funcionario;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class FuncionarioRepository extends BaseRepositoryJBDC<Funcionario>{


    public FuncionarioRepository(NamedParameterJdbcTemplate template) {
        super ( Funcionario.class, template );
    }
}
