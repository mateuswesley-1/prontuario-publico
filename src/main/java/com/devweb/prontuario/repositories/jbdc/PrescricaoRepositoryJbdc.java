package com.devweb.prontuario.repositories.jbdc;

import com.devweb.prontuario.base.BaseRepositoryJBDC;
import com.devweb.prontuario.entities.Prescricao;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class PrescricaoRepositoryJbdc extends BaseRepositoryJBDC<Prescricao> {

    public PrescricaoRepositoryJbdc(NamedParameterJdbcTemplate jdbcTemplate) {
        super ( Prescricao.class, jdbcTemplate );
    }
}
