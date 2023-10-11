package com.devweb.prontuario.dto.credenciais;

import com.devweb.prontuario.controllers.CredencialController;
import com.devweb.prontuario.dto.EntityRequestDto;
import com.devweb.prontuario.dto.EntityResponseDTO;

import java.time.LocalDate;

public class CredenciaisResponseDTO extends EntityResponseDTO<CredencialController> {

    private String username;
    private String password;
    private String IdFuncionario;

}
