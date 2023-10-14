package com.devweb.prontuario.repositories;

import com.devweb.prontuario.BaseRepositoryJBDC;
import com.devweb.prontuario.entities.Precricao;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class PrecricaoRepository extends BaseRepositoryJBDC<Precricao> {

    public PrecricaoRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        super ( Precricao.class, jdbcTemplate );
    }
}
