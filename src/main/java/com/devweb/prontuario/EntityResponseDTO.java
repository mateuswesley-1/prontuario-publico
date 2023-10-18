package com.devweb.prontuario;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import org.springframework.hateoas.RepresentationModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;



@Getter
@Setter
@NoArgsConstructor
public abstract class EntityResponseDTO {
    private String id;
    private LocalDateTime deleted_at;
    private LocalDateTime updated_at;
    private LocalDateTime created_at;
}
