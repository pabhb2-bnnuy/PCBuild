package com.pcb.build.controller;

import com.pcb.build.model.*;
import com.pcb.build.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping("/configuracion")
public class ConfiguracionController {

    @Autowired
    private ConfiguracionRepository configuracionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProductoConfiguracionRepository productoConfiguracionRepository;

    // Nueva configuración
    @GetMapping("/nueva")
    public String nuevaConfiguracion(Model model, Principal principal) {
        Configuracion configuracion = new Configuracion();
        model.addAttribute("configuracion", configuracion);
        return "configuraciones/nueva-configuracion";
    }

    @PostMapping("/guardar")
    public String guardarConfiguracion(@ModelAttribute Configuracion configuracion, Principal principal) {
        String email = principal.getName();
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow();

        configuracion.setUsuario(usuario);
        String hoy = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        configuracion.setFechacreacion(hoy);

        configuracionRepository.save(configuracion);

        return "redirect:/menu";
    }

    // Ver / editar configuración
    @GetMapping("/{id}")
    public String verConfiguracion(@PathVariable Integer id, Model model, Principal principal) {
        String email = principal.getName();
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow();

        Optional<Configuracion> opt = configuracionRepository.findById(id);
        if (opt.isEmpty()) {
            return "redirect:/menu";
        }

        Configuracion configuracion = opt.get();
        if (!configuracion.getUsuario().getIdusuario().equals(usuario.getIdusuario())) {
            return "redirect:/menu";
        }

        // Cargar productos actuales de la configuración
        List<ProductoConfiguracion> lista = productoConfiguracionRepository.findByConfiguracionIdconfiguracion(id);
        Map<String, Producto> seleccionados = new HashMap<>();
        for (ProductoConfiguracion pc : lista) {
            Producto p = pc.getProducto();
            seleccionados.put(p.getCategoria(), p);
        }

        model.addAttribute("configuracion", configuracion);
        model.addAttribute("seleccionados", seleccionados);

        // Todas las categorías necesarias
        List<String> categorias = Arrays.asList(
                "Placa base",
                "Procesador",
                "RAM",
                "Alimentacion",
                "Grafica",
                "Almacenamiento",
                "Gabinete");

        model.addAttribute("categorias", categorias);

        // Todos los productos por categoría
        Map<String, List<Producto>> porCategoria = new HashMap<>();
        for (String cat : categorias) {
            porCategoria.put(cat, productoRepository.findByCategoria(cat));
        }
        model.addAttribute("porCategoria", porCategoria);

        return "configuraciones/ver-configuracion";
    }

    @PostMapping("/actualizar-productos")
    public String actualizarProductos(
            @RequestParam Map<String, String> params,
            @RequestParam Integer idconfiguracion,
            Principal principal) {

        String email = principal.getName();
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow();

        Optional<Configuracion> opt = configuracionRepository.findById(idconfiguracion);
        if (opt.isEmpty()) {
            return "redirect:/menu";
        }

        Configuracion configuracion = opt.get();
        if (!configuracion.getUsuario().getIdusuario().equals(usuario.getIdusuario())) {
            return "redirect:/menu";
        }

        // Compatibilidad placa base / procesador
        String placaBaseNombre = params.get("Placa base");
        String procesadorNombre = params.get("Procesador");

        String mensajeError = "";

        if (placaBaseNombre != null && !placaBaseNombre.isEmpty()) {
            Producto placa = productoRepository.findByNombre(placaBaseNombre).orElse(null);

            if (placa != null) {
                boolean placaAMD = placa.getNombre().toUpperCase().endsWith("AMD");
                boolean placaINTEL = placa.getNombre().toUpperCase().endsWith("INTEL");

                // Si hay un CPU seleccionado incompatible
                if (procesadorNombre != null && !procesadorNombre.isEmpty()) {
                    Producto cpu = productoRepository.findByNombre(procesadorNombre).orElse(null);

                    if (cpu != null) {
                        boolean cpuAMD = cpu.getMarca().equalsIgnoreCase("AMD");
                        boolean cpuINTEL = cpu.getMarca().equalsIgnoreCase("Intel");

                        if ((placaAMD && cpuINTEL) || (placaINTEL && cpuAMD)) {
                            // Quitar el CPU
                            params.put("Procesador", "");
                            mensajeError = placaAMD
                                    ? "Has cambiado a placa AMD. Se ha quitado el CPU. Necesitas un procesador AMD."
                                    : "Has cambiado a placa INTEL. Se ha quitado el CPU. Necesitas un procesador INTEL.";
                        }
                    }
                }
            }
        }

        // Borrar productos actuales de la configuración
        List<ProductoConfiguracion> actuales = productoConfiguracionRepository
                .findByConfiguracionIdconfiguracion(idconfiguracion);
        productoConfiguracionRepository.deleteAll(actuales);

        // Guardar nuevos
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String categoria = entry.getKey();
            String nombreProducto = entry.getValue();

            if (nombreProducto != null && !nombreProducto.isEmpty()) {
                Optional<Producto> optProducto = productoRepository.findByNombre(nombreProducto);
                if (optProducto.isPresent()) {
                    Producto producto = optProducto.get();

                    if (!producto.getCategoria().equals(categoria)) {
                        continue;
                    }

                    ProductoConfiguracion pc = new ProductoConfiguracion();
                    ProductoConfiguracionId id = new ProductoConfiguracionId();
                    id.setIdProducto(producto.getIdproducto());
                    id.setIdConfiguracion(configuracion.getIdconfiguracion());
                    pc.setId(id);
                    pc.setProducto(producto);
                    pc.setConfiguracion(configuracion);

                    productoConfiguracionRepository.save(pc);
                }
            }
        }

        if (!mensajeError.isEmpty()) {
            return "redirect:/configuracion/" + idconfiguracion + "?error=" +
                    URLEncoder.encode(mensajeError, StandardCharsets.UTF_8);
        }

        return "redirect:/menu";
    }

    @GetMapping("/eliminar/{id}")
public String eliminarConfiguracion(@PathVariable Integer id, Principal principal) {
    String email = principal.getName();
    Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow();

    Configuracion configuracion = configuracionRepository.getReferenceById(id);
    if (!configuracion.getUsuario().getIdusuario().equals(usuario.getIdusuario())) {
        return "redirect:/menu";
    }

    // Borrar productos asociados primero
    List<ProductoConfiguracion> productos = productoConfiguracionRepository.findByConfiguracionIdconfiguracion(id);
    productoConfiguracionRepository.deleteAll(productos);

    // Luego borrar la configuración
    configuracionRepository.delete(configuracion);

    return "redirect:/menu";
}
}