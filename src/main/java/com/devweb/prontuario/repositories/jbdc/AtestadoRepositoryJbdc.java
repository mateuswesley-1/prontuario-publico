package com.devweb.prontuario.repositories.jbdc;

import com.devweb.prontuario.base.BaseRepositoryJBDC;
import com.devweb.prontuario.entities.Atestado;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class AtestadoRepositoryJbdc extends BaseRepositoryJBDC<Atestado> {

    public AtestadoRepositoryJbdc(NamedParameterJdbcTemplate jdbcTemplate) {
        super ( Atestado.class, jdbcTemplate );
    }
}
