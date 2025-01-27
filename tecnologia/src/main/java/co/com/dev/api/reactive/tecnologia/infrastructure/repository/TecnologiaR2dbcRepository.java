package co.com.dev.api.reactive.tecnologia.infrastructure.repository;

import co.com.dev.api.reactive.tecnologia.domain.model.Tecnologia;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface TecnologiaR2dbcRepository extends ReactiveCrudRepository<Tecnologia, Long> {
    Mono<Boolean> existsByNombre(String nombre);
}
