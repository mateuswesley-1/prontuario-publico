package com.devweb.prontuario.dto.Funcionario;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.devweb.prontuario.BaseMapper;
import com.devweb.prontuario.entities.Funcionario;


@Component
public class FuncionarioMapper extends BaseMapper<Funcionario, FuncionarioRequestDTO, FuncionarioResponseDTO>{
    

    public FuncionarioMapper(ModelMapper mapper){
        super(mapper);
    }
    public Funcionario requestDtoToEntity(FuncionarioRequestDTO dto){
        return super.requestDtoToEntity (dto, Funcionario.class);
    }

}
