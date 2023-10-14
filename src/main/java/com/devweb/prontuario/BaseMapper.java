package com.devweb.prontuario;

import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.UUID;


public class BaseMapper<
        T extends BaseEntity,
        E extends EntityRequestDto,
        R extends EntityResponseDTO<?>> {

    protected ModelMapper mapper;

    public BaseMapper(ModelMapper mapper){
        this.mapper = mapper;
    }
    public  T requestDtoToEntity(E dto, Class<T> entityType){
        T entity = mapper.map(dto, entityType);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        entity.setId(UUID.randomUUID().toString());
        return entity;
    }

    public R entityToResponseDto(T entity, Class<R> dtoType){
        return mapper.map(entity, dtoType);
    }

}

