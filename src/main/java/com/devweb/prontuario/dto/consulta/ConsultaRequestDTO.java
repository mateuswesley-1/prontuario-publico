package com.devweb.prontuario.dto.consulta;

import com.devweb.prontuario.base.EntityRequestDTO;
import com.devweb.prontuario.dto.atestado.AtestadoRequestDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
public class ConsultaRequestDTO extends EntityRequestDTO {
    private String idMedico;
    private String idPaciente;
    private String anamnese;
    private List<String> prescricoes;
    private AtestadoRequestDTO atestado;
}
