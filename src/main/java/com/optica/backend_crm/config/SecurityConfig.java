package com.optica.backend_crm.config;

import com.optica.backend_crm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private UserService userService;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Permitimos el origen de tu futuro proyecto Angular
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/me").authenticated()
                        // 1. RUTAS DE ADMINISTRADOR (Gestión de Personal)
                        .requestMatchers("/api/users/**").hasRole("ADMIN")

                        // 2. RUTAS OPERATIVAS (Todo el equipo)
                        .requestMatchers("/api/sales_notes/**").hasAnyRole("ADMIN", "VENDEDOR", "OPTOMETRISTA")
                        .requestMatchers("/api/inventory/**").hasAnyRole("ADMIN", "VENDEDOR", "OPTOMETRISTA")
                        .requestMatchers("/api/patients/**").hasAnyRole("ADMIN", "VENDEDOR", "OPTOMETRISTA")
                        .requestMatchers("/api/medical_history/**").hasAnyRole("ADMIN", "OPTOMETRISTA")

                        // 3. CUALQUIER OTRA RUTA
                        .anyRequest().authenticated()
                )

                .formLogin(withDefaults()) // Habilita el formulario de login por defecto (como el de tu diseño)
                .httpBasic(withDefaults()); // Permite autenticación básica para Postman

        return http.build();
    }
}