package com.devweb.prontuario.repositories.jbdc;

import com.devweb.prontuario.base.BaseRepositoryJBDC;
import com.devweb.prontuario.entities.Consulta;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class ConsultaRepositoryJbdc extends BaseRepositoryJBDC<Consulta> {

    public ConsultaRepositoryJbdc(NamedParameterJdbcTemplate jdbcTemplate) {
        super ( Consulta.class, jdbcTemplate );
    }
}
