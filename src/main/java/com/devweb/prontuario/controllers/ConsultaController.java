package com.devweb.prontuario.controllers;

import com.devweb.prontuario.base.BaseController;
import com.devweb.prontuario.base.BaseMapper;
import com.devweb.prontuario.dao.ConsultaDao;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devweb.prontuario.dto.consulta.ConsultaRequestDTO;
import com.devweb.prontuario.dto.consulta.ConsultaResponseDTO;

import com.devweb.prontuario.entities.Consulta;
import com.devweb.prontuario.services.ConsultaService;

@RestController
@RequestMapping("/consultas")
public class ConsultaController extends BaseController<Consulta, ConsultaDao,
        ConsultaService,
        ConsultaRequestDTO,
        ConsultaResponseDTO,
        BaseMapper<Consulta, ConsultaRequestDTO, ConsultaResponseDTO>
        > {

    public ConsultaController(BaseMapper<Consulta, ConsultaRequestDTO, ConsultaResponseDTO> mapper, ConsultaService service) {
        super ( mapper, service, Consulta.class, ConsultaResponseDTO.class );
    }


}
