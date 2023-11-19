package com.devweb.prontuario.entities;


import com.devweb.prontuario.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Component
@Entity
@Table(name="medicamento_tb")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Medicamento extends BaseEntity {
    @Size(min = 5, max = 250)
    public String nome;
    @Min(1)
    @Max(2000)
    public float dose;
}
