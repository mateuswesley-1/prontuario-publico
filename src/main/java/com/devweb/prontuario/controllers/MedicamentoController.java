package com.devweb.prontuario.controllers;

import com.devweb.prontuario.base.BaseController;
import com.devweb.prontuario.base.BaseMapper;
import com.devweb.prontuario.dao.MedicamentoDao;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devweb.prontuario.dto.medicamento.MedicamentoRequestDTO;
import com.devweb.prontuario.dto.medicamento.MedicamentoResponseDTO;
import com.devweb.prontuario.entities.Medicamento;
import com.devweb.prontuario.services.MedicamentoService;

@RestController
@RequestMapping("/medicamentos")
public class MedicamentoController extends BaseController<Medicamento, MedicamentoDao,MedicamentoService, MedicamentoRequestDTO, MedicamentoResponseDTO, BaseMapper<Medicamento, MedicamentoRequestDTO, MedicamentoResponseDTO>> {

    public MedicamentoController(BaseMapper<Medicamento, MedicamentoRequestDTO, MedicamentoResponseDTO> mapper, MedicamentoService service) {
        super(mapper, service, Medicamento.class, MedicamentoResponseDTO.class);
    }
    
}
