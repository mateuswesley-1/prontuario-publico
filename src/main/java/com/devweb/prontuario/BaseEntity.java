package com.devweb.prontuario;

import java.time.LocalDateTime;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseEntity {

    private String id;
    private LocalDateTime deleted_at;
    private LocalDateTime updated_at;
    private LocalDateTime created_at;

}
