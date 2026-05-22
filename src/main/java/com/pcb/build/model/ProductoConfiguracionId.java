package com.pcb.build.model;

import java.io.Serializable;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class ProductoConfiguracionId implements Serializable {

    private Integer idProducto;
    private Integer idConfiguracion;
}