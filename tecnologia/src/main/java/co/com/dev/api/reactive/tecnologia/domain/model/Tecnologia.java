package co.com.dev.api.reactive.tecnologia.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Tecnologia {

    private Integer id;
    private String nombre;
    private String descripcion;

    public boolean esValido() {
        return nombre != null &&
                !nombre.trim().isEmpty() &&
                nombre.length() <= 50 &&
                descripcion != null &&
                !descripcion.trim().isEmpty() &&
                descripcion.length() <= 90;
    }
}
