package com.devweb.prontuario.dto.credenciais;

import com.devweb.prontuario.controllers.CredencialController;
import com.devweb.prontuario.EntityResponseDTO;

public class CredenciaisResponseDTO extends EntityResponseDTO<CredencialController> {

    private String username;
    private String password;

}
