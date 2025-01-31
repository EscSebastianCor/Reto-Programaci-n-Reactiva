package co.com.dev.api.reactive.capacidad.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Capacidad {
    private String uniqueId;
    private String nombre;
    private String descripcion;
    private Set<Integer> tecnologiasIds;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Capacidad( String nombre, String descripcion, Set<Integer> tecnologiasIds) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tecnologiasIds = tecnologiasIds;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}


