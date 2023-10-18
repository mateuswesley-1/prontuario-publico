package com.devweb.prontuario.entities;


import com.devweb.prontuario.BaseEntity;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Component
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Medicamento extends BaseEntity {

    public String nome;
    public float dose;

}
