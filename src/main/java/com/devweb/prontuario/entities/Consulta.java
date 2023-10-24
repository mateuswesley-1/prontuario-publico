package com.devweb.prontuario.entities;


import com.devweb.prontuario.BaseEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    @NotNull
    @Pattern(
            regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$",
            flags = Pattern.Flag.CASE_INSENSITIVE
    )
    private String medico_id;

    @NotNull
    @Pattern(
            regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$",
            flags = Pattern.Flag.CASE_INSENSITIVE
    )
    private String paciente_id;
    @NotNull
    @Size(min = 1, max = 9999)
    private String anamnese;
    @NotNull
    @Pattern(
            regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$",
            flags = Pattern.Flag.CASE_INSENSITIVE
    )
    private String atestado_id;
}
