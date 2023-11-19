package com.devweb.prontuario.repositories.jbdc;

import java.util.Optional;

import com.devweb.prontuario.base.BaseRepositoryJBDC;
import com.devweb.prontuario.entities.Credenciais;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class CredenciaisRepositoryJbdc extends BaseRepositoryJBDC<Credenciais> {
    public CredenciaisRepositoryJbdc(NamedParameterJdbcTemplate jdbcTemplate) {
        super ( Credenciais.class, jdbcTemplate );
    }

    public  Optional<Credenciais> findByUsername(String username){
        String sql = "SELECT * FROM " + this.NomeTabela() + " WHERE username = ?";
        RowMapper<Credenciais> rowMapper = this.getRowMapper();
        return this.getJdbcTemplate().getJdbcOperations().query(sql, rowMapper, username)
                .stream()
                .findFirst();
    }

    @Override
    public Optional<Credenciais> findById(String id) {
        throw new UnsupportedOperationException ( "Esse método não é permitido para um objeto da classe " + Credenciais.class );
    }

    @Override
    public Page<Credenciais> findAll(Pageable pageable) {
        throw new UnsupportedOperationException ( "Esse método não é permitido para um objeto da classe " + Credenciais.class );
    }


}

