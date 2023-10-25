package com.devweb.prontuario;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@Api(tags = "BaseController")
public abstract class
BaseController<T extends BaseEntity,
                                     Repo extends BaseRepositoryJBDC<T>,
                                     S extends BaseService<T, Repo>,
                                     E extends EntityRequestDTO,
                                     R extends EntityResponseDTO,
                                     M extends BaseMapper<T, E, R>
                                     > {


    protected M mapper;
    protected S service;
    protected Class<T> entityType;
    protected Class<R> entityResponseDTOType;

    public BaseController(M mapper, S service, Class<T> entityType, Class<R> entityResponseDTOType){
        this.mapper = mapper;
        this.service = service;
        this.entityType = entityType;
        this.entityResponseDTOType = entityResponseDTOType;
    }

    @GetMapping
    @ApiOperation(value = "Obter uma página de entidades", response = BaseController.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso, retorna uma Página"),
            @ApiResponse(code = 200, message = "Se não existir nenhuma instância, retorna uma página sem conteúdo.")
    })
    public ResponseEntity<Page<R>> getAll(@PageableDefault( sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        Page<T> page = service.getAll(pageable);
        List<T> listaEntity = page.getContent();
        List<R> dtoList = listaEntity.stream()
                                        .map(entity -> mapper.entityToResponseDto(entity, entityResponseDTOType))
                                                     .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(new PageImpl<>(dtoList, pageable, dtoList.size()));
    }




    @GetMapping("/{id}")
    @ApiOperation(value = "Obter uma entidade pelo ID", response = BaseController.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso, retorna a entidade solicitada."),
            @ApiResponse(code = 404, message = "Entidade não encontrada.")
    })
    public ResponseEntity<R> getById(@PathVariable String id){
        T response = service.getById(id);
        R responseDTO = mapper.entityToResponseDto(response, entityResponseDTOType);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping
    @ApiOperation(value = "Criar uma nova instância de entidade.", response = BaseController.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso, retorna a entidade criada."),
            @ApiResponse(code = 400, message = "Existe algum erro na requisição.")
    })
    public ResponseEntity<Object> create(@RequestBody E dto){
        T entity = mapper.requestDtoToEntity(dto, entityType);
        T created = service.create(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "Criar uma nova instância de entidade.", response = BaseController.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso, retorna a entidade criada."),
            @ApiResponse(code = 400, message = "Existe algum erro na requisição.")
    })
    public ResponseEntity<Object> delete(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}")
    public EntityResponseDTO patchOrUpdate(@PathVariable String id, @RequestBody E dto){
        T entity = mapper.requestDtoToEntity(dto, entityType);
        service.update(id, entity);
        return mapper.entityToResponseDto(service.getById(id), entityResponseDTOType);
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






