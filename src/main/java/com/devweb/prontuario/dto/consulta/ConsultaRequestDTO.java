package com.devweb.prontuario.dto.consulta;

import java.util.List;

import com.devweb.prontuario.EntityRequestDTO;

import lombok.Data;


@Data
public class ConsultaRequestDTO extends EntityRequestDTO {
    private String idMedico;
    private String idPaciente;
    private String anamnese;
    private List<String> prescricoes;
    private String idAtestado;
}
