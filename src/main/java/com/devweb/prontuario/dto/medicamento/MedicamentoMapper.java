package com.devweb.prontuario.dto.medicamento;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.devweb.prontuario.dto.BaseMapper;
import com.devweb.prontuario.entities.Medicamento;

@Component
public class MedicamentoMapper extends BaseMapper<Medicamento, MedicamentoRequestDTO, MedicamentoResponseDTO>{


    
    public MedicamentoMapper(ModelMapper mapper) {

        super(mapper);
    }

    
    public Medicamento requestDtoToEntity(MedicamentoRequestDTO dto){
        return super.requestDtoToEntity(dto, Medicamento.class);
    }

}