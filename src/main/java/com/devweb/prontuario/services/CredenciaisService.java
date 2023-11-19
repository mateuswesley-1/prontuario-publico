package com.devweb.prontuario.services;



import com.devweb.prontuario.base.BaseService;
import com.devweb.prontuario.dao.CredenciaisDao;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.devweb.prontuario.entities.Credenciais;

import java.util.Optional;


@Service
public class CredenciaisService extends BaseService<Credenciais, CredenciaisDao> implements UserDetailsService  {

    public BCryptPasswordEncoder encoder;

    public CredenciaisService(CredenciaisDao dao, BCryptPasswordEncoder encoder) {
        super(dao, Credenciais.class);
        this.encoder = encoder;
    }

    public Credenciais create(Credenciais c){
        c.setPassword(encoder.encode(c.getPassword()));
        return dao.save(c);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Credenciais> credenciais = dao.findByUsername(username);
        if (credenciais.isPresent()) return credenciais.get();
        throw new UsernameNotFoundException("Usuario não cadastrado com username "+ username);
    }
}
