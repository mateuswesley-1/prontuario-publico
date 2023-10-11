package com.devweb.prontuario.dto.credenciais;

import java.time.LocalDate;

import com.devweb.prontuario.dto.EntityRequestDto;
import com.devweb.prontuario.entities.Funcionario;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CredenciaisRequestDTO extends EntityRequestDto {
    private String username;
    private String password;
}
