package com.devweb.prontuario.repositories;

import com.devweb.prontuario.entities.Consulta;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class ConsultaRepository extends BaseRepositoryJBDC<Consulta>{

    public ConsultaRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        super ( Consulta.class, jdbcTemplate );
    }
}
