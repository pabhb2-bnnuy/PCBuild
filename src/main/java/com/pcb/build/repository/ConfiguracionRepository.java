package com.pcb.build.repository;

import com.pcb.build.model.Configuracion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConfiguracionRepository extends JpaRepository<Configuracion, Integer> {
    List<Configuracion> findByUsuarioIdusuario(Integer idusuario);

    List<Configuracion> findByNombre(String nombre);
}