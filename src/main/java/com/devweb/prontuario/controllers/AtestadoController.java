package com.devweb.prontuario.controllers;

import com.devweb.prontuario.base.BaseController;
import com.devweb.prontuario.base.BaseMapper;
import com.devweb.prontuario.dao.AtestadoDao;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devweb.prontuario.dto.atestado.AtestadoRequestDTO;
import com.devweb.prontuario.dto.atestado.AtestadoResponseDTO;
import com.devweb.prontuario.entities.Atestado;
import com.devweb.prontuario.services.AtestadoService;

@RestController
@RequestMapping("/atestados")
public class AtestadoController extends BaseController<Atestado,
        AtestadoDao,
        AtestadoService,
        AtestadoRequestDTO,
        AtestadoResponseDTO,
        BaseMapper<Atestado, AtestadoRequestDTO, AtestadoResponseDTO>
        > {


    public AtestadoController(BaseMapper<Atestado, AtestadoRequestDTO, AtestadoResponseDTO> mapper, AtestadoService service) {

        super ( mapper, service, Atestado.class, AtestadoResponseDTO.class );

    }

}
