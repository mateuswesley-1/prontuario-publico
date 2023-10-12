package com.devweb.prontuario.dto.credenciais;

import com.devweb.prontuario.dto.BaseMapper;
import com.devweb.prontuario.dto.Funcionario.FuncionarioMapper;
import com.devweb.prontuario.entities.Credenciais;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CredenciaisMapper extends BaseMapper<Credenciais, CredenciaisRequestDTO, CredenciaisResponseDTO> {


    public CredenciaisMapper(ModelMapper mapper){
        super(mapper);
    }

    public Credenciais requestDtoToEntity(CredenciaisRequestDTO dto){
        return super.requestDtoToEntity(dto, Credenciais.class);

    }

}
