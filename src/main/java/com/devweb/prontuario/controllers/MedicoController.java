package com.devweb.prontuario.controllers;


import com.devweb.prontuario.BaseController;
import com.devweb.prontuario.BaseMapper;
import com.devweb.prontuario.dto.medico.MedicoRequestDTO;
import com.devweb.prontuario.dto.medico.MedicoResponseDTO;
import com.devweb.prontuario.entities.Medico;
import com.devweb.prontuario.repositories.MedicoRepository;
import com.devweb.prontuario.services.MedicoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController extends BaseController<Medico, MedicoRepository, MedicoService, MedicoRequestDTO, MedicoResponseDTO, BaseMapper<Medico, MedicoRequestDTO, MedicoResponseDTO>> {
    public MedicoController(BaseMapper<Medico, MedicoRequestDTO, MedicoResponseDTO> mapper, MedicoService service){
        super(mapper, service, Medico.class, MedicoResponseDTO.class);
    }
}

