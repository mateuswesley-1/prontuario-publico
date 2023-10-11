package com.devweb.prontuario.dto.medico;

import com.devweb.prontuario.controllers.FuncionarioController;
import com.devweb.prontuario.controllers.MedicoController;
import com.devweb.prontuario.dto.EntityResponseDTO;
import com.devweb.prontuario.dto.Funcionario.FuncionarioResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class MedicoResponseDTO extends EntityResponseDTO<MedicoController> {
    public MedicoResponseDTO(){
        super(MedicoController.class);
    }

    private long crm;
    private String especialidade;
    private String CPF;
    private String nome;
    private LocalDate dataNascimento;
    private String endereco;
    private String email;

}


