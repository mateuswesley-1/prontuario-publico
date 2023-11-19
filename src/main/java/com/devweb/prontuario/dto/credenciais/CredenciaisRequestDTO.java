package com.devweb.prontuario.dto.credenciais;

import com.devweb.prontuario.base.EntityRequestDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CredenciaisRequestDTO extends EntityRequestDTO {
    private String username;
    private String password;
}
