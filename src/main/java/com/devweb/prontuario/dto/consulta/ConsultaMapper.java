package com.devweb.prontuario.dto.consulta;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.devweb.prontuario.BaseMapper;
import com.devweb.prontuario.entities.Consulta;
import com.devweb.prontuario.services.FuncionarioService;
import com.devweb.prontuario.services.MedicoService;


@Component
public class ConsultaMapper extends BaseMapper<Consulta, ConsultaRequestDTO, ConsultaResponseDTO>{

    MedicoService medicoService;
    FuncionarioService funcionarioService;


    public ConsultaMapper(ModelMapper mapper, FuncionarioService funcionarioService, MedicoService medicoService) {
        super(mapper);
        this.medicoService = medicoService;
        this.funcionarioService = funcionarioService;
    }

    public Consulta requestDtoToEntity(ConsultaRequestDTO dto){
        Consulta consulta = super.requestDtoToEntity(dto, Consulta.class);
        consulta.setMedico(medicoService.getById(dto.getIdMedico()));
        consulta.setPaciente(funcionarioService.getById(dto.getIdPaciente()));
        return consulta;
    }

}
