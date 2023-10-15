package com.devweb.prontuario.controllers;

import com.devweb.prontuario.BaseController;
import com.devweb.prontuario.BaseMapper;
import com.devweb.prontuario.repositories.AtestadoRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devweb.prontuario.dto.atestado.AtestadoRequestDTO;
import com.devweb.prontuario.dto.atestado.AtestadoResponseDTO;
import com.devweb.prontuario.entities.Atestado;
import com.devweb.prontuario.services.AtestadoService;

@RestController
@RequestMapping("/atestados")
public class AtestadoController extends BaseController<Atestado,
                                                       AtestadoRepository,
                                                       AtestadoService,
                                                       AtestadoRequestDTO,
                                                       AtestadoResponseDTO,
                                                       BaseMapper<Atestado, AtestadoRequestDTO, AtestadoResponseDTO>
                                                       > {
                                                    
    
    public AtestadoController(BaseMapper<Atestado, AtestadoRequestDTO, AtestadoResponseDTO> mapper, AtestadoService service) {
        super(mapper, service, Atestado.class, AtestadoResponseDTO.class);
    }
    
}
