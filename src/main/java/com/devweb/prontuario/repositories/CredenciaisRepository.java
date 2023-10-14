package com.devweb.prontuario.repositories;

import java.util.Optional;

import com.devweb.prontuario.BaseRepositoryJBDC;
import com.devweb.prontuario.entities.Credenciais;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class CredenciaisRepository extends BaseRepositoryJBDC<Credenciais> {
    public CredenciaisRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        super ( Credenciais.class, jdbcTemplate );
    }

    public  Optional<Credenciais> findByUsername(String username){
        String sql = "SELECT * FROM " + this.NomeTabela() + " WHERE username = ?";
        RowMapper<Credenciais> rowMapper = this.getRowMapper();
        return this.getJdbcTemplate().getJdbcOperations().query(sql, rowMapper, username)
                .stream()
                .findFirst();
    }
}

