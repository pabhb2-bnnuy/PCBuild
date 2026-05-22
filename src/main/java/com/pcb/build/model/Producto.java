package com.pcb.build.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "producto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idproducto;

    private String categoria;
    private Integer precio;

    @Column(unique = true)
    private String nombre;
    private String modelo;
    private Integer stock;
    private String marca;
}