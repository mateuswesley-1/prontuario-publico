package com.devweb.prontuario.dto;

import java.time.LocalDateTime;

import com.devweb.prontuario.controllers.BaseController;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import org.springframework.hateoas.RepresentationModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;



@Getter
@Setter
@NoArgsConstructor
public abstract class EntityResponseDTO<C extends BaseController<?, ?, ?, ?, ?, ?>> extends RepresentationModel<EntityResponseDTO<C>> {

    @JsonIgnore
    private Class<C> entityType;

    public EntityResponseDTO(Class<C> entityType) {
        this.entityType = entityType;
    }

    private String id;
    private LocalDateTime deletedAt;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public void addLinks() {
        this.add(linkTo(entityType).slash(id).withSelfRel());
        this.add(linkTo(entityType).slash(id).withRel("delete"));
        this.add(linkTo(entityType).withRel("allEntities"));
    }
}
