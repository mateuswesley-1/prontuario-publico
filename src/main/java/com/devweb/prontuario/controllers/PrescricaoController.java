package com.devweb.prontuario.controllers;


import com.devweb.prontuario.dto.prescricao.PrescricaoMapper;
import com.devweb.prontuario.dto.prescricao.PrescricaoRequestDTO;
import com.devweb.prontuario.dto.prescricao.PrescricaoResponseDTO;
import com.devweb.prontuario.entities.Precricao;
import com.devweb.prontuario.repositories.PrecricaoRepository;
import com.devweb.prontuario.services.PrecricaoService;
public class PrescricaoController extends BaseController<Precricao, PrecricaoRepository,PrecricaoService, PrescricaoRequestDTO, PrescricaoResponseDTO, PrescricaoMapper>{

    public PrescricaoController(PrescricaoMapper mapper, PrecricaoService service) {

        super(mapper, service, Precricao.class, PrescricaoResponseDTO.class);
    }

}