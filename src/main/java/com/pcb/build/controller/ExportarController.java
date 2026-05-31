package com.pcb.build.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pcb.build.model.Configuracion;
import com.pcb.build.model.Producto;
import com.pcb.build.model.ProductoConfiguracion;
import com.pcb.build.model.Usuario;
import com.pcb.build.repository.ConfiguracionRepository;
import com.pcb.build.repository.ProductoConfiguracionRepository;
import com.pcb.build.repository.UsuarioRepository;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/configuracion")
public class ExportarController {

    @Autowired
    private ConfiguracionRepository configuracionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoConfiguracionRepository productoConfiguracionRepository;

    @GetMapping("/exportar/{id}")
    public void exportarConfiguracion(@PathVariable Integer id, HttpServletResponse response, Principal principal) {
        String email = principal.getName();
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow();

        Optional<Configuracion> opt = configuracionRepository.findById(id);
        if (opt.isEmpty()) {
            return;
        }

        Configuracion configuracion = opt.get();
        if (!configuracion.getUsuario().getIdusuario().equals(usuario.getIdusuario())) {
            return;
        }

        // Cargar productos de la configuración
        List<ProductoConfiguracion> lista = productoConfiguracionRepository.findByConfiguracionIdconfiguracion(id);
        Map<String, Producto> seleccionados = new HashMap<>();
        for (ProductoConfiguracion pc : lista) {
            Producto p = pc.getProducto();
            seleccionados.put(p.getCategoria(), p);
        }

        // Crear contenido del TXT
        StringBuilder contenido = new StringBuilder();
        contenido.append("CONFIGURACIÓN PC - ").append(configuracion.getNombre()).append("\n");
        contenido.append("Fecha de creación: ").append(configuracion.getFechacreacion()).append("\n\n");

        List<String> categorias = Arrays.asList(
                "Placa base",
                "Procesador",
                "RAM",
                "Alimentacion",
                "Grafica",
                "Almacenamiento",
                "Gabinete");

        double total = 0;

        for (String cat : categorias) {
            contenido.append(cat.toUpperCase()).append(":\n");
            Producto prod = seleccionados.get(cat);
            if (prod != null) {
                contenido.append("  - ").append(prod.getNombre()).append("\n");
                contenido.append("    Marca: ").append(prod.getMarca()).append("\n");
                contenido.append("    Modelo: ").append(prod.getModelo()).append("\n");
                contenido.append("    Precio: ").append(prod.getPrecio()).append("€\n");
                total += prod.getPrecio();
            } else {
                contenido.append("  No seleccionado\n");
            }
            contenido.append("\n");
        }

        contenido.append("TOTAL: ").append(total).append("€\n");

        // Configurar respuesta para descargar TXT
        response.setHeader("Content-Disposition",
                "attachment; filename=\"" + configuracion.getNombre().replaceAll("[^a-zA-Z0-9]", "_") + ".txt\"");
        response.setContentType("text/plain;charset=UTF-8");

        try {
            response.getOutputStream().write(contenido.toString().getBytes(StandardCharsets.UTF_8));
            response.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
