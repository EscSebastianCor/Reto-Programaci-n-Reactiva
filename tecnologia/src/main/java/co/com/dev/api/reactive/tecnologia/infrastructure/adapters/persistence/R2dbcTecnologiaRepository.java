package co.com.dev.api.reactive.tecnologia.infrastructure.adapters.persistence;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface R2dbcTecnologiaRepository extends R2dbcRepository<TecnologiaEntity, Integer> {
    Mono<Boolean> existsByNombre(String nombre);
}
