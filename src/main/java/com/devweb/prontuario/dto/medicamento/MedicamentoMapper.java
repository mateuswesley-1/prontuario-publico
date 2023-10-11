package com.devweb.prontuario.dto.medicamento;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.devweb.prontuario.dto.BaseMapper;
import com.devweb.prontuario.entities.Medicamento;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class MedicamentoMapper extends BaseMapper<Medicamento, MedicamentoRequestDTO, MedicamentoResponseDTO>{


    
    public MedicamentoMapper(ModelMapper mapper, Medicamento medicamento) {

        super(mapper, medicamento);
    }

    
    public Medicamento requestDtoToEntity(MedicamentoRequestDTO dto){
        return super.requestDtoToEntity(dto, Medicamento.class);
    }

    public MedicamentoResponseDTO entityToResponseDto(Medicamento medicamento){
        return super.entityToResponseDto(medicamento, MedicamentoResponseDTO.class);
    }


}