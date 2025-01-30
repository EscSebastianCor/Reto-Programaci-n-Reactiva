package co.com.dev.api.reactive.tecnologia.domain.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tecnologia {

    private Integer uniqueId;
    private String nombre;
    private String descripcion;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Tecnologia( String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }



}

