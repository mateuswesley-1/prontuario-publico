package com.devweb.prontuario.services;



import com.devweb.prontuario.BaseService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.devweb.prontuario.entities.Credenciais;
import com.devweb.prontuario.repositories.CredenciaisRepository;

import java.util.Optional;


@Service
public class CredenciaisService extends BaseService<Credenciais, CredenciaisRepository> implements UserDetailsService  {

    public BCryptPasswordEncoder encoder;

    public CredenciaisService(CredenciaisRepository repository, BCryptPasswordEncoder encoder) {
        super(repository, Credenciais.class);
        this.encoder = encoder;
    }

    public void add(Credenciais c){
        c.setPassword(encoder.encode(c.getPassword()));
        repository.save(c);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Credenciais> credenciais = repository.findByUsername(username);
        if (credenciais.isPresent()){
            return credenciais.get();
        }else{
            throw new UsernameNotFoundException("Usuario não cadastrado com username "+username);
        }
    }
}
