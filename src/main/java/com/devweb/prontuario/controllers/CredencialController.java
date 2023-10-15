package com.devweb.prontuario.controllers;

import com.devweb.prontuario.BaseController;
import com.devweb.prontuario.BaseMapper;
import com.devweb.prontuario.dto.credenciais.CredenciaisRequestDTO;
import com.devweb.prontuario.dto.credenciais.CredenciaisResponseDTO;
import com.devweb.prontuario.repositories.CredenciaisRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                BaseMapper<Credenciais, CredenciaisRequestDTO, CredenciaisResponseDTO>> {

    BaseMapper<Credenciais, CredenciaisRequestDTO, CredenciaisResponseDTO> mapper;

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
}
