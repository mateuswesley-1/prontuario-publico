package com.devweb.prontuario.dto.medico;


import com.devweb.prontuario.base.EntityRequestDTO;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class MedicoRequestDTO extends EntityRequestDTO {
    private long crm;
    private String especialidade;
    private String funcionarioId;
}

