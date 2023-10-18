package com.devweb.prontuario.repositories;

import com.devweb.prontuario.BaseRepositoryJBDC;
import com.devweb.prontuario.entities.Prescricao;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class PrescricaoRepository extends BaseRepositoryJBDC<Prescricao> {

    public PrescricaoRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        super ( Prescricao.class, jdbcTemplate );
    }
}
