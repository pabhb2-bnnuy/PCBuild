package com.pcb.build.repository;

import com.pcb.build.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    List<Producto> findByCategoria(String categoria);
    Optional<Producto> findByNombre(String nombre);
}