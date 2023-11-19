package com.devweb.prontuario.repositories.jbdc;

import com.devweb.prontuario.base.BaseRepositoryJBDC;
import com.devweb.prontuario.entities.Funcionario;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class FuncionarioRepositoryJbdc extends BaseRepositoryJBDC<Funcionario> {
    public FuncionarioRepositoryJbdc(NamedParameterJdbcTemplate template) {
        super ( Funcionario.class, template );
    }
}
