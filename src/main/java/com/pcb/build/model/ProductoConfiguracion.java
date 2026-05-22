package com.pcb.build.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "producto_configuracion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoConfiguracion {

    @EmbeddedId
    private ProductoConfiguracionId id;

    @ManyToOne
    @MapsId("idProducto")
    @JoinColumn(name = "idproducto")
    private Producto producto;

    @ManyToOne
    @MapsId("idConfiguracion")
    @JoinColumn(name = "idconfiguracion")
    private Configuracion configuracion;

}
