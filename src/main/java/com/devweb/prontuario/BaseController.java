package com.devweb.prontuario;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.devweb.prontuario.BaseRepositoryJBDC;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devweb.prontuario.BaseMapper;
import com.devweb.prontuario.EntityRequestDto;
import com.devweb.prontuario.EntityResponseDTO;
import com.devweb.prontuario.BaseEntity;
import com.devweb.prontuario.services.BaseService;

import jakarta.persistence.MappedSuperclass;


@MappedSuperclass
@RestController
public abstract class BaseController<T extends BaseEntity,
                                     Repo extends BaseRepositoryJBDC<T>,
                                     S extends BaseService<T, Repo>,
                                     E extends EntityRequestDto,
                                     R extends EntityResponseDTO<?>,
                                     M extends BaseMapper<T, E, R>
                                     > {


    private M mapper;
    private S service;
    private Class<T> entityType;

    private Class<R> entityResponseDTOType;
    public BaseController(M mapper, S service, Class<T> entityType, Class<R> entityResponseDTOType){
        this.mapper = mapper;
        this.service = service;
        this.entityType = entityType;
        this.entityResponseDTOType = entityResponseDTOType;
    }

    @GetMapping
    public ResponseEntity<Page<R>> getAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        Page<T> page = service.getAll(pageable);
        List<T> listaEntity = page.getContent();
        List<R> dtoList = listaEntity.stream()
                                        .map(entity -> mapper.entityToResponseDto(entity, entityResponseDTOType))
                                                     .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(new PageImpl<>(dtoList, pageable, dtoList.size()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<R> getById(@PathVariable String id){
        T response = service.getById(id);
        R responseDTO = mapper.entityToResponseDto(response, entityResponseDTOType);
        responseDTO.addLinks();
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody E dto){
        T entity = mapper.requestDtoToEntity(dto, entityType);
        service.add(entity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}")
    public EntityResponseDTO<?> patchOrUpdate(@PathVariable String id, @RequestBody E dto){
        T entity = mapper.requestDtoToEntity(dto, entityType);
        service.update(id, entity);
        R responseDTO = mapper.entityToResponseDto(service.getById(id), entityResponseDTOType);
        responseDTO.addLinks();
        return responseDTO;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody E dto){
        service.update(id, mapper.requestDtoToEntity(dto, entityType));
        URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .buildAndExpand()
                    .toUri();
        return ResponseEntity.ok(uri);
    }


}






