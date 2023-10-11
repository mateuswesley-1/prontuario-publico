package com.devweb.prontuario.dto.atestado;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.devweb.prontuario.dto.BaseMapper;
import com.devweb.prontuario.entities.Atestado;



@Component
public class AtestadoMapper extends BaseMapper<Atestado, AtestadoRequestDTO, AtestadoResponseDTO>{



    public AtestadoMapper(ModelMapper mapper, Atestado atestado) {
        super(mapper, atestado);
    }

    
    public Atestado requestDtoToEntity(AtestadoRequestDTO dto){
        return this.mapper.map(dto, Atestado.class);
    }

    public AtestadoResponseDTO entityToResponseDto(Atestado atestado){
        return this.mapper.map(atestado, AtestadoResponseDTO.class);
        
    }

    
}
