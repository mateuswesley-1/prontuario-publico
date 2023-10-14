package com.devweb.prontuario.dto.credenciais;

import com.devweb.prontuario.EntityRequestDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CredenciaisRequestDTO extends EntityRequestDto {
    private String username;
    private String password;
}
