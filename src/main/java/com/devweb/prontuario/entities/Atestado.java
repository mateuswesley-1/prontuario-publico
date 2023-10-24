package com.devweb.prontuario.entities;

import com.devweb.prontuario.BaseEntity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class Atestado extends BaseEntity {
    @Min(1)
    @Max(30)
    private int duracao;

    @Size(min = 1, max = 9999)
    @NotNull
    private String descricao;
}
