package com.devweb.prontuario.dto.medico;

import com.devweb.prontuario.dto.BaseMapper;
import com.devweb.prontuario.dto.Funcionario.FuncionarioMapper;
import com.devweb.prontuario.dto.Funcionario.FuncionarioRequestDTO;
import com.devweb.prontuario.entities.Funcionario;
import com.devweb.prontuario.entities.Medico;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class MedicoMapper extends BaseMapper<Medico, MedicoRequestDTO, MedicoResponseDTO> {

    FuncionarioMapper funcionarioMapper;
    public MedicoMapper(ModelMapper mapper, FuncionarioMapper funcionarioMapper){
        super(mapper);
        this.funcionarioMapper = funcionarioMapper;
    }

    public FuncionarioRequestDTO medicoDtoToFuncionarioDto(MedicoRequestDTO dto){
        return this.mapper.map(dto, FuncionarioRequestDTO.class);
    }
    public Medico requestDtoToEntity(MedicoRequestDTO dto){
        Medico medico = this.mapper.map(dto, Medico.class);
        Funcionario funcionario = funcionarioMapper.requestDtoToEntity(this.medicoDtoToFuncionarioDto(dto), Funcionario.class);
        medico.setFuncionario(funcionario);
        medico.getFuncionario().setCargo("Médico");
        return medico;
    }
}
