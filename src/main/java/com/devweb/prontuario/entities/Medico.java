package com.devweb.prontuario.entities;

import com.devweb.prontuario.BaseEntity;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Medico extends BaseEntity {
    private String funcionario_id;
    private int crm;
    private String especialidade;
}
