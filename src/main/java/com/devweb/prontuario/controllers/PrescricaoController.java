package com.devweb.prontuario.controllers;


import com.devweb.prontuario.BaseController;
import com.devweb.prontuario.BaseMapper;
import com.devweb.prontuario.dto.prescricao.PrescricaoRequestDTO;
import com.devweb.prontuario.dto.prescricao.PrescricaoResponseDTO;
import com.devweb.prontuario.entities.Prescricao;
import com.devweb.prontuario.repositories.PrescricaoRepository;
import com.devweb.prontuario.services.PrecricaoService;
public class PrescricaoController extends BaseController<Prescricao, PrescricaoRepository,PrecricaoService, PrescricaoRequestDTO, PrescricaoResponseDTO, BaseMapper<Prescricao, PrescricaoRequestDTO, PrescricaoResponseDTO>> {

    public PrescricaoController(BaseMapper<Prescricao, PrescricaoRequestDTO, PrescricaoResponseDTO> mapper, PrecricaoService service) {

        super(mapper, service, Prescricao.class, PrescricaoResponseDTO.class);
    }

}