package com.devweb.prontuario.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;



@MappedSuperclass
public abstract class BaseEntity {
    @Getter
    @Setter
    @Id
    protected String id;
    protected LocalDateTime deleted_at;
    protected LocalDateTime updated_at;
    protected LocalDateTime created_at;

    public void setCreatedAt(LocalDateTime time){
        this.created_at = time;
    }

    public void setUpdatedAt(LocalDateTime time){
        this.updated_at = time;
    }
}
