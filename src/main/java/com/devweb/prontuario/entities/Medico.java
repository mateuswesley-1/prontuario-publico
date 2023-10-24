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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Medico extends BaseEntity {
    @NotNull
    @Pattern(
            regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$",
            flags = Pattern.Flag.CASE_INSENSITIVE
    )
    private String funcionario_id;
    private int crm;
    @NotNull
    @Size(min=5, max=50)
    private String especialidade;
}
