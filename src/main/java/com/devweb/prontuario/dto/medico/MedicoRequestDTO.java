package com.devweb.prontuario.dto.medico;



import com.devweb.prontuario.EntityRequestDto;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class MedicoRequestDTO extends EntityRequestDto {

    private long crm;
    private String especialidade;
    private String CPF;
    private String nome;
    private LocalDate dataNascimento;
    private String endereco;
    private String email;

}

