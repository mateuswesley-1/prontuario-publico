package com.devweb.prontuario.dto.Funcionario;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.devweb.prontuario.dto.BaseMapper;
import com.devweb.prontuario.entities.Funcionario;


@Component
public class FuncionarioMapper extends BaseMapper<Funcionario, FuncionarioRequestDTO, FuncionarioResponseDTO>{
    

    public FuncionarioMapper(ModelMapper mapper, Funcionario funcionario){
        super(mapper, funcionario);
    }
    public Funcionario requestDtoToEntity(FuncionarioRequestDTO dto){
        return super.requestDtoToEntity (dto, Funcionario.class);
    }

    public FuncionarioResponseDTO entityToResponseDto(Funcionario entity){
        return super.entityToResponseDto (entity, FuncionarioResponseDTO.class);
    }

    
}
