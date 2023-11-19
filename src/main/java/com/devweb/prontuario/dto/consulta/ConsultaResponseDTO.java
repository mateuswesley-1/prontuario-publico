package com.devweb.prontuario.dto.consulta;

import java.util.List;

import com.devweb.prontuario.base.EntityResponseDTO;
import com.devweb.prontuario.dto.Funcionario.FuncionarioResponseDTO;
import com.devweb.prontuario.dto.atestado.AtestadoResponseDTO;
import com.devweb.prontuario.dto.medico.MedicoResponseDTO;
import com.devweb.prontuario.dto.prescricao.PrescricaoResponseDTO;

import lombok.*;

@Getter
@Setter
public class ConsultaResponseDTO extends EntityResponseDTO {
    private MedicoResponseDTO medico;
    private FuncionarioResponseDTO paciente;
    private String anamnese;
    private List<PrescricaoResponseDTO> prescricao;
    private AtestadoResponseDTO atestado;
}
