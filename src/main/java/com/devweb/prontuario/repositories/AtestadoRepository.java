package com.devweb.prontuario.repositories;

import com.devweb.prontuario.BaseRepositoryJBDC;
import com.devweb.prontuario.entities.Atestado;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class AtestadoRepository extends BaseRepositoryJBDC<Atestado> {

    public AtestadoRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        super ( Atestado.class, jdbcTemplate );
    }
}
