package co.com.dev.api.reactive.capacidad.domain.port.out;

import co.com.dev.api.reactive.capacidad.domain.model.Capacidad;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CapacidadRepository {
    Mono<Capacidad> save(Capacidad capacidad);
    Mono<Boolean> existsByNombre(String nombre);
    Flux<Capacidad> findAll(Pageable pageable);
}
