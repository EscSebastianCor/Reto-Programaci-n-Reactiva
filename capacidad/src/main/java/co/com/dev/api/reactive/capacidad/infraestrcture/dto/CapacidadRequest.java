package co.com.dev.api.reactive.capacidad.infraestrcture.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CapacidadRequest {
    @NotBlank(message = "El nombre es requerido")
    private String nombre;

    @NotBlank(message = "La descripción es requerida")
    private String descripcion;

    @Size(min = 3, max = 20, message = "Debe tener entre 3 y 20 tecnologías")
    private Set<Integer> tecnologiasIds;
}
