package com.devweb.prontuario.repositories.jbdc;

import com.devweb.prontuario.base.BaseRepositoryJBDC;
import com.devweb.prontuario.entities.Medico;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class MedicoRepositoryJbdc extends BaseRepositoryJBDC<Medico> {

    public MedicoRepositoryJbdc(NamedParameterJdbcTemplate jdbcTemplate) {
        super ( Medico.class, jdbcTemplate );
    }
}
