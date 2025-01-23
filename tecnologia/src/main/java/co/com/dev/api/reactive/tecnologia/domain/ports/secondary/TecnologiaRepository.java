package co.com.dev.api.reactive.tecnologia.domain.ports.secondary;

import co.com.dev.api.reactive.tecnologia.domain.model.Tecnologia;
import reactor.core.publisher.Mono;

public interface TecnologiaRepository {
    Mono<Tecnologia> save(Tecnologia tecnologia);
    Mono<Boolean> existsByNombre(String nombre);
}
