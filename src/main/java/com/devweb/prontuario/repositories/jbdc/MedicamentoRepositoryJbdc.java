package com.devweb.prontuario.repositories.jbdc;

import com.devweb.prontuario.base.BaseRepositoryJBDC;
import com.devweb.prontuario.entities.Medicamento;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class MedicamentoRepositoryJbdc extends BaseRepositoryJBDC<Medicamento> {

    public MedicamentoRepositoryJbdc(NamedParameterJdbcTemplate jdbcTemplate) {
        super ( Medicamento.class, jdbcTemplate );
    }
}
