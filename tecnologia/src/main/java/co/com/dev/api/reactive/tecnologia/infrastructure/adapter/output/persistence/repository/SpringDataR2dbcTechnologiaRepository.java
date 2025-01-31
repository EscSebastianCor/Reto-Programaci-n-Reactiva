package co.com.dev.api.reactive.tecnologia.infrastructure.adapter.output.persistence.repository;

import co.com.dev.api.reactive.tecnologia.infrastructure.adapter.output.persistence.entity.TecnologiaEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SpringDataR2dbcTechnologiaRepository extends R2dbcRepository<TecnologiaEntity, Long> {
    Mono<Boolean> existsByNombre(String nombre);
    Flux<TecnologiaEntity> findAllBy(Pageable pageable);
}
