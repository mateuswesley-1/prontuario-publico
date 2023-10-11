package com.devweb.prontuario.dto.consulta;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.devweb.prontuario.dto.BaseMapper;
import com.devweb.prontuario.entities.Consulta;
import com.devweb.prontuario.services.FuncionarioService;
import com.devweb.prontuario.services.MedicoService;


@Component
public class ConsultaMapper extends BaseMapper<Consulta, ConsultaRequestDTO, ConsultaResponseDTO>{

    MedicoService medicoService;
    FuncionarioService funcionarioService;


    public ConsultaMapper(ModelMapper mapper, Consulta consulta, FuncionarioService funcionarioService, MedicoService medicoService) {
        super(mapper, consulta);
        this.medicoService = medicoService;
        this.funcionarioService = funcionarioService;
    }

    public Consulta requestDtoToEntity(ConsultaRequestDTO dto){
        Consulta consulta = super.requestDtoToEntity(dto, Consulta.class);
        consulta.setMedico(medicoService.getById(dto.getIdMedico()));
        consulta.setPaciente(funcionarioService.getById(dto.getIdPaciente()));
        return consulta;
    }

    public ConsultaResponseDTO entityToResponseDto(Consulta consulta){
        return super.entityToResponseDto(consulta, ConsultaResponseDTO.class);
    }
}
