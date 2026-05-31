package com.pcb.build.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.pcb.build.model.Configuracion;
import com.pcb.build.repository.ConfiguracionRepository;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LandingController {

    @Autowired
    private ConfiguracionRepository configuracionRepository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/registrarse")
    public String registrarse() {
        return "registrarse";
    }

    @GetMapping("/inicioSesion")
    public String login() {
        return "inicioSesion";
    }

    @GetMapping("/menu")
    public String menu(Model model) {
        List<Configuracion> configuraciones = configuracionRepository.findAll();
        model.addAttribute("configuraciones", configuraciones);
        return "menu";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword() {
        return "forgot-password";
    }

}