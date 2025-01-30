package co.com.dev.api.reactive.tecnologia.domain.port.in;

import co.com.dev.api.reactive.tecnologia.domain.model.Tecnologia;
import reactor.core.publisher.Mono;

public interface CrearTecnologiaUserCase {
    Mono<Tecnologia> crearTecnologia(String nombre, String descripcion);
}
