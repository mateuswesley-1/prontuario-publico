package com.devweb.prontuario.dto.atestado;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.devweb.prontuario.BaseMapper;
import com.devweb.prontuario.entities.Atestado;



@Component
public class AtestadoMapper extends BaseMapper<Atestado, AtestadoRequestDTO, AtestadoResponseDTO>{



    public AtestadoMapper(ModelMapper mapper) {
        super(mapper);
    }

    
    public Atestado requestDtoToEntity(AtestadoRequestDTO dto){
        return this.mapper.map(dto, Atestado.class);
    }


}
