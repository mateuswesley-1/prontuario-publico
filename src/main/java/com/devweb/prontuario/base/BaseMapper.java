package com.devweb.prontuario.base;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;


@Component
public class BaseMapper<
        T extends BaseEntity,
        E extends EntityRequestDTO,
        R extends EntityResponseDTO> {

    protected ModelMapper mapper;

    public BaseMapper(ModelMapper mapper){
        this.mapper = mapper;
    }
    public T requestDtoToEntity(E dto, Class<T> entityType){
        T entity = mapper.map(dto, entityType);
        entity.setCreatedAt (LocalDateTime.now());
        entity.setUpdatedAt (LocalDateTime.now());
        entity.setId(UUID.randomUUID().toString());
        return entity;
    }

    public R entityToResponseDto(T entity, Class<R> dtoType){
        R response = mapper.map(entity, dtoType);
        return response;
    }

}

