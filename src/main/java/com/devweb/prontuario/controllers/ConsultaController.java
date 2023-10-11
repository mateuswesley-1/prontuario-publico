package com.devweb.prontuario.controllers;

import com.devweb.prontuario.repositories.ConsultaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devweb.prontuario.dto.consulta.ConsultaMapper;
import com.devweb.prontuario.dto.consulta.ConsultaRequestDTO;
import com.devweb.prontuario.dto.consulta.ConsultaResponseDTO;

import com.devweb.prontuario.entities.Consulta;
import com.devweb.prontuario.services.ConsultaService;

@RestController
@RequestMapping("/consultas")
public class ConsultaController extends BaseController<Consulta, ConsultaRepository,
                                                       ConsultaService,
                                                       ConsultaRequestDTO,
                                                       ConsultaResponseDTO,
                                                       ConsultaMapper
                                                       >{

    public ConsultaController(ConsultaMapper mapper, ConsultaService service) {

        super(mapper, service, Consulta.class, ConsultaResponseDTO.class);
    }


}
