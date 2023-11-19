package com.devweb.prontuario.controllers;


import com.devweb.prontuario.base.BaseController;
import com.devweb.prontuario.base.BaseMapper;
import com.devweb.prontuario.dao.MedicoDao;
import com.devweb.prontuario.dto.medico.MedicoRequestDTO;
import com.devweb.prontuario.dto.medico.MedicoResponseDTO;
import com.devweb.prontuario.entities.Medico;
import com.devweb.prontuario.services.FuncionarioService;
import com.devweb.prontuario.services.MedicoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController extends BaseController<Medico, MedicoDao, MedicoService, MedicoRequestDTO, MedicoResponseDTO, BaseMapper<Medico, MedicoRequestDTO, MedicoResponseDTO>> {

    public MedicoController(BaseMapper<Medico, MedicoRequestDTO, MedicoResponseDTO> mapper, MedicoService medicoService){
        super(mapper, medicoService, Medico.class, MedicoResponseDTO.class);
    }
}

