package com.devweb.prontuario.entities;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "credenciais_tb")
@Getter
@Setter
@NoArgsConstructor
@ToString
@SQLDelete(sql = "UPDATE credenciais_tb SET deleted_at = CURRENT_TIMESTAMP WHERE id=?")
@Where(clause = "deleted_at is null")
public class Credenciais extends BaseEntity implements UserDetails  {
    @Id
    public String id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "funcionario_id")
    public Funcionario funcionario;

    @Column(unique = true)
    public String username;
    public String password;


    public LocalDateTime deleted_at;

    @UpdateTimestamp
    public LocalDateTime updated_at;
    @CreationTimestamp
    public LocalDateTime created_at;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(funcionario.getCargo().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }    
}
