package com.devweb.prontuario.dto.medico;

import com.devweb.prontuario.base.EntityResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class MedicoResponseDTO extends EntityResponseDTO {
    private long crm;
    private String especialidade;
    private String CPF;
    private String nome;
    private LocalDate dataNascimento;
    private String endereco;
    private String email;

}


