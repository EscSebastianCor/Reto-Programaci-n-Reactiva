package co.com.dev.api.reactive.tecnologia.infrastructure.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TecnologiaResponse {
    private Integer uniqueId;
    private String nombre;
    private String descripcion;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
