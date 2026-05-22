package com.pcb.build.repository;

import com.pcb.build.model.ProductoConfiguracion;
import com.pcb.build.model.ProductoConfiguracionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoConfiguracionRepository extends JpaRepository<ProductoConfiguracion, ProductoConfiguracionId> {
    List<ProductoConfiguracion> findByProductoIdproducto(Integer idProducto);
    List<ProductoConfiguracion> findByConfiguracionIdconfiguracion(Integer idConfiguracion);
}