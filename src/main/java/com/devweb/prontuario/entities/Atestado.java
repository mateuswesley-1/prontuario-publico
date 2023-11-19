package com.devweb.prontuario.entities;

import com.devweb.prontuario.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Component
@Entity
@Table(name="atestado_tb")
public class Atestado extends BaseEntity {
    @Min(1)
    @Max(30)
    private int duracao;

    @Size(min = 1, max = 9999)
    @NotNull
    private String descricao;
}
