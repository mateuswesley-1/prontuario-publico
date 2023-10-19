package com.devweb.prontuario.controllers;


import com.devweb.prontuario.BaseController;
import com.devweb.prontuario.BaseMapper;
import com.devweb.prontuario.dto.prescricao.PrescricaoRequestDTO;
import com.devweb.prontuario.dto.prescricao.PrescricaoResponseDTO;
import com.devweb.prontuario.entities.Prescricao;
import com.devweb.prontuario.repositories.PrescricaoRepository;
import com.devweb.prontuario.services.PrescricaoService;
public class PrescricaoController extends BaseController<Prescricao, PrescricaoRepository, PrescricaoService, PrescricaoRequestDTO, PrescricaoResponseDTO, BaseMapper<Prescricao, PrescricaoRequestDTO, PrescricaoResponseDTO>> {

    public PrescricaoController(BaseMapper<Prescricao, PrescricaoRequestDTO, PrescricaoResponseDTO> mapper, PrescricaoService service) {

        super(mapper, service, Prescricao.class, PrescricaoResponseDTO.class);
    }

}