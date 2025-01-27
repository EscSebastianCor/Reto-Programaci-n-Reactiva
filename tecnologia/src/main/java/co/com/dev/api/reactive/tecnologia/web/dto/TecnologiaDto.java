package co.com.dev.api.reactive.tecnologia.web.dto;

import co.com.dev.api.reactive.tecnologia.domain.model.Tecnologia;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
public class TecnologiaDto {

    private Long id;

    @NotBlank(message = "El nombre es requerido")
    @Size(max = 50, message = "El nombre no debe exceder el limite de 50 caracteres")
    private String nombre;
    @NotBlank(message = "La descripción es requerida")
    @Size(max = 90, message = "La descripción no debe exceder el limite de 90 caracteres")
    private String descripcion;


    public TecnologiaDto() {
    }

    public TecnologiaDto(Long id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
}
