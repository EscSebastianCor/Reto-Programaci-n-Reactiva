package co.com.dev.api.reactive.capacidad.infraestrcture.adapter.output.repository;

import co.com.dev.api.reactive.capacidad.infraestrcture.adapter.output.persistence.CapacidadEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface SpringDataR2dbcCapacidadRepository extends ReactiveCrudRepository<CapacidadEntity, String> {
    Mono<Boolean> existsByNombre(String nombre);
    Flux<CapacidadEntity> findAllBy(Pageable pageable);
}