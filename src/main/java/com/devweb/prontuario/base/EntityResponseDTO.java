package com.devweb.prontuario.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;



@Getter
@Setter
@NoArgsConstructor
public abstract class EntityResponseDTO {
    private String id;
    private LocalDateTime deletedAt;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
