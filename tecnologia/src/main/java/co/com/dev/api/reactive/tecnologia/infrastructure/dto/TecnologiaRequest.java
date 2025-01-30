package co.com.dev.api.reactive.tecnologia.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TecnologiaRequest {

    @NotBlank(message = "El nombre es un valor requerido")
    @Size(max = 50, message = "El nombre no puede exceder el limite de 50 caracteres")
    private String nombre;

    @NotBlank(message = "La descripcion es un valor requerido")
    @Size(max = 90, message = "La descripcion no puede exceder el limite de 90 caracteres")
    private String descripcion;
}
