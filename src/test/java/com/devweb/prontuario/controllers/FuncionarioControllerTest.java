// package com.devweb.prontuario.controllers;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.Mockito.when;

// import java.time.LocalDate;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.stream.Collectors;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageImpl;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.http.HttpStatus;

// import com.devweb.prontuario.dto.Funcionario.FuncionarioMapper;
// import com.devweb.prontuario.dto.Funcionario.FuncionarioResponseDTO;
// import com.devweb.prontuario.entities.Funcionario;
// import com.devweb.prontuario.repositories.FuncionarioRepository;
// import com.devweb.prontuario.services.FuncionarioService;

// @SpringBootTest
// @ExtendWith(MockitoExtension.class)
// public class FuncionarioControllerTest {
    
//     @InjectMocks
//     private FuncionarioController controller;

//     @Mock
//     private FuncionarioRepository repository;

//     @Mock
//     private FuncionarioService service = new FuncionarioService(repository);

//     @Mock
//     private FuncionarioMapper mapper;


//     @Test
//     public void deve_retornar_sucesso_getAll(){
//         List<Funcionario> funcionarios = new ArrayList<>();
//         LocalDate nascimento = LocalDate.parse("1997-09-16");
        
//         funcionarios.add(new Funcionario("1", "mateus", nascimento, "mateus", "mateus@gmail.com", "frentista"));
//         funcionarios.add(new Funcionario("2", "amanda", nascimento, "amanda", "amanda@gmail.com", "frentista"));
//         funcionarios.add(new Funcionario("3", "rodolfo", nascimento, "yoshiga", "rodolfo@gmail.com", "frentista"));
//         funcionarios.add(new Funcionario("4", "pandora", nascimento, "xepera", "pandora@gmail.com", "frentista"));
//         funcionarios.add(new Funcionario("5", "luno", nascimento, "rua", "luno@gmail.com", "frentista"));
        
//         PageRequest pageable = PageRequest.of(0, 5);
//         Page<Funcionario> page = new PageImpl<>(funcionarios, pageable, funcionarios.size());

//         when(service.getAll(pageable)).thenReturn(page);

        
//         Page<FuncionarioResponseDTO> response = controller.getAll(pageable);
        
//         assertEquals(HttpStatus.valueOf(200), 200);
        
        
//        List<FuncionarioResponseDTO> funcionariosDTO = funcionarios
//                                                         .stream()
//                                                         .map(funcionario -> mapper.entityToResponseDto(funcionario)).collect(Collectors.toList());

//         assertEquals(funcionariosDTO, response.getContent());

//     }
// }
