package com.devweb.prontuario.controllers;

import com.devweb.prontuario.repositories.FuncionarioRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devweb.prontuario.dto.Funcionario.FuncionarioMapper;
import com.devweb.prontuario.dto.Funcionario.FuncionarioRequestDTO;
import com.devweb.prontuario.dto.Funcionario.FuncionarioResponseDTO;
import com.devweb.prontuario.entities.Funcionario;
import com.devweb.prontuario.services.FuncionarioService;
@RestController
@RequestMapping("/funcionarios")
public class  FuncionarioController extends BaseController<Funcionario, FuncionarioRepository, FuncionarioService, FuncionarioRequestDTO, FuncionarioResponseDTO, FuncionarioMapper>{
    public FuncionarioController(FuncionarioMapper mapper, FuncionarioService service) {
        super(mapper, service, Funcionario.class, FuncionarioResponseDTO.class);
    }

}