package com.pcb.build.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.pcb.build.model.Configuracion;
import com.pcb.build.repository.ConfiguracionRepository;

@Controller
public class ConfiguracionController {
    @Autowired
    private ConfiguracionRepository configuracionRepository;

    @GetMapping("/menu")
    public String menu(Model model) {
        List<Configuracion> configuraciones = configuracionRepository.findAll();
        model.addAttribute("configuraciones", configuraciones);
        return "menu";
    }
}
