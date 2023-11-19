package com.devweb.prontuario.base;

import java.time.LocalDateTime;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private LocalDateTime deletedAt;

    private LocalDateTime updatedAt;

    private LocalDateTime createdAt;

}
