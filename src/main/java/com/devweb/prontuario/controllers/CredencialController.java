package com.devweb.prontuario.controllers;

import com.devweb.prontuario.BaseController;
import com.devweb.prontuario.BaseMapper;
import com.devweb.prontuario.EntityResponseDTO;
import com.devweb.prontuario.dto.credenciais.CredenciaisRequestDTO;
import com.devweb.prontuario.dto.credenciais.CredenciaisResponseDTO;
import com.devweb.prontuario.repositories.CredenciaisRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devweb.prontuario.entities.Credenciais;
import com.devweb.prontuario.services.CredenciaisService;
import org.springframework.web.server.MethodNotAllowedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/credenciais")
public class CredencialController
        extends BaseController<
                Credenciais,
                CredenciaisRepository,
                CredenciaisService,
                CredenciaisRequestDTO,
                CredenciaisResponseDTO,
                BaseMapper<Credenciais, CredenciaisRequestDTO, CredenciaisResponseDTO>> {

    BaseMapper<Credenciais, CredenciaisRequestDTO, CredenciaisResponseDTO> mapper;
    List<HttpMethod> allowedMethods = new ArrayList<> ( Arrays.asList (HttpMethod.DELETE, HttpMethod.POST ) );

    public CredencialController(CredenciaisService service, BaseMapper<Credenciais, CredenciaisRequestDTO, CredenciaisResponseDTO> mapper) {
        super ( mapper, service, Credenciais.class, CredenciaisResponseDTO.class );
        this.mapper = mapper;
    }

    @Override
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody CredenciaisRequestDTO dto) {
        Credenciais credenciais = this.mapper.requestDtoToEntity ( dto, Credenciais.class);
        service.add ( credenciais );
        return ResponseEntity.status ( HttpStatus.CREATED ).build ( );
    }

    @Override
    public ResponseEntity<Page<CredenciaisResponseDTO>> getAll(Pageable pageable) {
        throw new MethodNotAllowedException ( HttpMethod.GET,  allowedMethods);
    }

    @Override
    public ResponseEntity<CredenciaisResponseDTO> getById(String id) {
        throw new MethodNotAllowedException ( HttpMethod.GET,  allowedMethods);
    }

    @Override
    public ResponseEntity<Object> delete(String id) {
        return super.delete ( id );
    }

    @Override
    public EntityResponseDTO patchOrUpdate(String id, CredenciaisRequestDTO dto) {
        throw new MethodNotAllowedException ( HttpMethod.GET,  allowedMethods);
    }

    @Override
    public ResponseEntity<Object> update(String id, CredenciaisRequestDTO dto) {
        throw new MethodNotAllowedException ( HttpMethod.PUT,  allowedMethods);
    }

}
