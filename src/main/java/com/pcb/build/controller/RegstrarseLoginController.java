package com.pcb.build.controller;

import com.pcb.build.model.Rol;
import com.pcb.build.model.Usuario;
import com.pcb.build.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class RegstrarseLoginController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(
            @RequestParam String nombre,
            @RequestParam String email,
            @RequestParam String password) {
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setRol(Rol.USER);
        usuario.setPassword(passwordEncoder.encode(password));

        usuarioRepository.save(usuario);
        return "redirect:/inicioSesion";
    }

}