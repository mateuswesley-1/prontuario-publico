package com.devweb.prontuario.dao.interfaces;

import com.devweb.prontuario.entities.Credenciais;

import java.util.Optional;

public interface CredenciaisDaoInterface extends DataAcessObject<Credenciais> {

    Optional<Credenciais> findByUsername(String username);

    Credenciais save(Credenciais entityInstance);
}
