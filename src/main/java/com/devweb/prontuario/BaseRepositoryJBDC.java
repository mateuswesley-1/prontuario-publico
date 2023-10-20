package com.devweb.prontuario;


import lombok.Data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Data
public abstract class BaseRepositoryJBDC<T extends BaseEntity> {

    private final Class<T> entityType;
    private final NamedParameterJdbcTemplate jdbcTemplate;
    StringBuilder columns;
    StringBuilder placeHolders;
    MapSqlParameterSource paramSource = new MapSqlParameterSource();
    public BaseRepositoryJBDC(Class<T> entityType, NamedParameterJdbcTemplate jdbcTemplate){
        this.entityType = entityType;
        this.jdbcTemplate = jdbcTemplate;
    }
    public Optional<T> findById(String id){
        String sql = "SELECT * FROM " + this.NomeTabela ( ) + " WHERE id = ? AND deleted_at is null";

        RowMapper<T> rowMapper = this.getRowMapper();
        return jdbcTemplate.
                getJdbcOperations()
                .query(sql, rowMapper, id)
                .stream()
                .findFirst();

    }

    public Page<T> findAll(Pageable pageable){
        String sql = "SELECT * FROM " + this.NomeTabela();
        RowMapper<T> rowMapper = this.getRowMapper();
        List<T> result = jdbcTemplate.query(sql, rowMapper);
        return new PageImpl<>(result, pageable, result.size());
    }

    public Optional<T> save(T entityInstance){
        columns = new StringBuilder();
        placeHolders = new StringBuilder();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.ClassFields(entityInstance);
        StringBuilder sql = new StringBuilder();
        sql
            .append("INSERT INTO ").append(this.NomeTabela())
            .append(" (").append(columns).append(") ")
            .append(" VALUES (").append(placeHolders)
            .append(")")
            .append ( " RETURNING" ).append ( " id;" );
        jdbcTemplate.update(sql.toString(), this.paramSource, keyHolder);
        entityInstance.setId ( keyHolder.getKeyAs ( String.class ) );
        return Optional.of(entityInstance);
     }

     public void delete(String id){
         String sql = "UPDATE " + this.NomeTabela() + " SET deleted_at = ? WHERE id = ?";
         jdbcTemplate.getJdbcOperations().update (sql, LocalDateTime.now(), id);
     }
    public String NomeTabela(){
        String[] nomeTabela = this.entityType.getName().split("\\.");
        return nomeTabela[nomeTabela.length - 1].toLowerCase() + "_tb";
     }

    public void ClassFields(T entityInstance){
        Class<?> superClass = this.entityType.getSuperclass();
        List<Field> fields = new ArrayList<> ( Arrays.asList ( this.entityType.getDeclaredFields ( ) ) );
        fields.addAll (Arrays.asList(superClass.getDeclaredFields ()));
        for (Field field : fields){
            this.columns.append(field.getName()).append(",");
            this.placeHolders.append(":").append(field.getName()).append(",");
            field.setAccessible(true);
            try {
                this.paramSource.addValue (field.getName(), field.get(entityInstance));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        this.columns.deleteCharAt(this.columns.length() - 1);
        this.placeHolders.deleteCharAt(this.placeHolders.length() - 1);
     }

    public RowMapper<T> getRowMapper() {
         this.paramSource =  new MapSqlParameterSource();
         return (rs, rowNum) -> {
             T instance;
             try{
                 instance = entityType.getDeclaredConstructor().newInstance();
                 Class<?> superClass = this.entityType.getSuperclass();
                 List<Field> fields = new ArrayList<> ( Arrays.asList ( this.entityType.getDeclaredFields ( ) ) );
                 fields.addAll (Arrays.asList(superClass.getDeclaredFields ()));
                 for (Field field : fields) {
                     field.setAccessible(true);
                     Object value = rs.getObject(field.getName ());
                     if (field.getType() == LocalDate.class  && value instanceof java.sql.Date) {
                         value = ((java.sql.Date) value).toLocalDate ( );
                     }
                     if (field.getType () == LocalDateTime.class && value instanceof java.sql.Timestamp) {
                         value = ((java.sql.Timestamp) value).toLocalDateTime ( );
                     }
                     field.set(instance, value);
                     this.paramSource.addValue(field.getName(), value);
                 }
             } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException |
                      InstantiationException e) {
                 throw new RuntimeException ( e );
             }

             return entityType.cast(instance);
         };

     }
}
