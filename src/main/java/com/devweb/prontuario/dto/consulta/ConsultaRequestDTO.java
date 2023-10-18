package com.devweb.prontuario.dto.consulta;

import java.util.List;

import com.devweb.prontuario.EntityRequestDTO;
import com.devweb.prontuario.dto.atestado.AtestadoRequestDTO;
import com.devweb.prontuario.dto.prescricao.PrescricaoRequestDTO;

import lombok.Data;


@Data
public class ConsultaRequestDTO extends EntityRequestDTO {
    private String idMedico;
    private String idPaciente;
    private String anamnese;
    private List<PrescricaoRequestDTO> prescricao;
    private AtestadoRequestDTO atestado;
}
