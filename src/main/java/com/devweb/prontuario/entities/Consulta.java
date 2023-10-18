package com.devweb.prontuario.entities;


import com.devweb.prontuario.BaseEntity;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Consulta extends BaseEntity {
    private String medico_id;
    private String paciente_id;
    private String anamnese;
    private String atestado_id;
}
