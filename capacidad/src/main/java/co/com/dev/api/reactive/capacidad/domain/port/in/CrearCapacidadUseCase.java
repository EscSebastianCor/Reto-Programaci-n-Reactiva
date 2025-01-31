package co.com.dev.api.reactive.capacidad.domain.port.in;

import co.com.dev.api.reactive.capacidad.domain.model.Capacidad;
import reactor.core.publisher.Mono;

import java.util.Set;

public interface CrearCapacidadUseCase {
    Mono<Capacidad> crearCapacidad(String nombre, String descripcion, Set<Integer> tecnologiasIds);
}
