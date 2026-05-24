package com.pcb.build.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LandingController {

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
    public String menu() {
        return "menu";
    }
}