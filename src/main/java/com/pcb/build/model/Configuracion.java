package com.pcb.build.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "configuracion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Configuracion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idconfiguracion;

    private String fechacreacion;
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "idusuario", nullable = false)
    private Usuario usuario;

   @OneToMany(mappedBy = "configuracion", fetch = FetchType.EAGER)
private List<ProductoConfiguracion> productoConfiguraciones;

}