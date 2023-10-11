package com.devweb.prontuario.dto.credenciais;

import com.devweb.prontuario.dto.BaseMapper;
import com.devweb.prontuario.dto.Funcionario.FuncionarioMapper;
import com.devweb.prontuario.dto.Funcionario.FuncionarioRequestDTO;
import com.devweb.prontuario.entities.Credenciais;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.devweb.prontuario.entities.Funcionario;

@Component
public class CredenciaisMapper extends BaseMapper<Credenciais, CredenciaisRequestDTO, CredenciaisResponseDTO> {

    FuncionarioMapper funcionarioMapper;

    public CredenciaisMapper(ModelMapper mapper, FuncionarioMapper funcionarioMapper, Credenciais credenciais){
        super(mapper, credenciais);
        this.funcionarioMapper = funcionarioMapper;
    }

    public FuncionarioRequestDTO credenciaisDtoToFuncionarioDto(CredenciaisRequestDTO dto){
        return this.mapper.map(dto, FuncionarioRequestDTO.class);
    }
    public Credenciais requestDtoToEntity(CredenciaisRequestDTO dto){
        Credenciais credenciais =  super.requestDtoToEntity(dto, Credenciais.class);
        Funcionario funcionario = funcionarioMapper.requestDtoToEntity(this.credenciaisDtoToFuncionarioDto(dto), Funcionario.class);
        credenciais.setFuncionario(funcionario);
        return credenciais;
    }

}
