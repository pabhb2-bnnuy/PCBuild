package com.pcb.build.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public AuthenticationManager authenticationManager(
                        AuthenticationConfiguration config)
                        throws Exception {

                return config.getAuthenticationManager();
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http)
                        throws Exception {

                return http

                                .csrf(csrf -> csrf.disable())

                                .authorizeHttpRequests(auth -> auth
                                                // URLS sin iniciar sesión.
                                                .requestMatchers(
                                                                "/",
                                                                "/css/**",
                                                                "/media/**",
                                                                "/registrarse",
                                                                "/inicioSesion",
                                                                "/register")
                                                .permitAll()

                                                // URLS para admin
                                                .requestMatchers("/admin/**")
                                                .hasRole("ADMIN")

                                                // URLS para gestor y colaborador
                                                .requestMatchers("/menu")
                                                .hasAnyRole("ADMIN", "USER")

                                                .anyRequest()
                                                .authenticated())

                                .formLogin(form -> form

                                                // Página login (GET)
                                                .loginPage("/inicioSesion")

                                                // Procesar login (POST)
                                                .loginProcessingUrl("/inicioSesion")

                                                // Names del formulario
                                                .usernameParameter("mail")
                                                .passwordParameter("password")

                                                .successHandler((request, response, authentication) -> {

                                                        var authorities = authentication.getAuthorities();

                                                        boolean isAdmin = authorities.stream()
                                                                        .anyMatch(a -> a.getAuthority()
                                                                                        .equals("ROLE_ADMIN"));

                                                        if (isAdmin) {
                                                                response.sendRedirect("/admin");
                                                        } else {
                                                                response.sendRedirect("/menu");
                                                        }
                                                })

                                                .permitAll())

                                // PERSISTENCIA DE SESIÓN
                                .rememberMe(remember -> remember

                                                // name="" del checkbox
                                                .rememberMeParameter("remember-me")

                                                // clave interna
                                                .key("vlc-clave-secreta-2026"))

                                // Cerrar sesión.
                                .logout(logout -> logout

                                                .logoutUrl("/cerrarSesion")

                                                .logoutSuccessUrl("/inicioSesion")

                                                .invalidateHttpSession(true)

                                                .deleteCookies(
                                                                "JSESSIONID",
                                                                "remember-me"))

                                .build();
        }
}