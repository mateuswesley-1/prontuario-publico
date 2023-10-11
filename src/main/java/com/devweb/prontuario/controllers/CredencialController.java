package com.devweb.prontuario.controllers;

import com.devweb.prontuario.dto.EntityResponseDTO;
import com.devweb.prontuario.dto.credenciais.CredenciaisRequestDTO;
import com.devweb.prontuario.dto.credenciais.CredenciaisResponseDTO;
import com.devweb.prontuario.repositories.CredenciaisRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devweb.prontuario.dto.credenciais.CredenciaisMapper;
import com.devweb.prontuario.entities.Credenciais;
import com.devweb.prontuario.services.CredenciaisService;

@RestController
@RequestMapping("/credenciais")
public class CredencialController
        extends BaseController<
        Credenciais,
        CredenciaisRepository,
        CredenciaisService,
        CredenciaisRequestDTO,
        CredenciaisResponseDTO,
        CredenciaisMapper> {
    CredenciaisService service;
    CredenciaisMapper mapper;

    public CredencialController(CredenciaisService service, CredenciaisMapper mapper) {
        super(mapper, service, Credenciais.class, CredenciaisResponseDTO.class);
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<CredenciaisResponseDTO> create(@RequestBody CredenciaisRequestDTO dto){
        Credenciais credenciais = this.mapper.requestDtoToEntity(dto);
        service.create(credenciais);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
