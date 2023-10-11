package com.devweb.prontuario.repositories;

import com.devweb.prontuario.entities.Medico;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class MedicoRepository extends BaseRepositoryJBDC<Medico>{

    public MedicoRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        super ( Medico.class, jdbcTemplate );
    }
}
