package com.optica.backend_crm.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User implements UserDetails { // Implementamos la interfaz para la Auth

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer idUser;

    @Column(unique = true, nullable = false)
    private String email; // Este será el identificador principal en el login
    private String password;

    @Column(name = "name_employee")
    private String nameEmployee; // Para saber quién realizó cada acción en el sistema

    private Boolean active = true;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_rol")
    private Role role;

    // --- MÉTODOS DE USERDETAILS (Spring Security) ---

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (role == null || role.getRoleName() == null) {
            return List.of();
        }
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
    }

    @Override
    public String getUsername() {
        return this.email; // Spring Security seguirá usando el email como "identificador"
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isEnabled() {
        return this.active; // Mapeamos tu campo 'active' al estado de la cuenta
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Seteamos en true para que la cuenta no expire por defecto
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Seteamos en true para que la cuenta no se bloquee por defecto
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Seteamos en true para que las credenciales no caduquen
    }
}