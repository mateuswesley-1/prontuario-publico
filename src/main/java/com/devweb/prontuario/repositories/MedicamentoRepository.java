package com.devweb.prontuario.repositories;

import com.devweb.prontuario.entities.Medicamento;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class MedicamentoRepository extends BaseRepositoryJBDC<Medicamento>{

    public MedicamentoRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        super ( Medicamento.class, jdbcTemplate );
    }
}
