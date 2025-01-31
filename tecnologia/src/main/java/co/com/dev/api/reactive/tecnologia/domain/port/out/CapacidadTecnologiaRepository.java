package co.com.dev.api.reactive.tecnologia.domain.port.out;

import co.com.dev.api.reactive.tecnologia.infrastructure.adapter.output.persistence.entity.CapacidadTecnologiaEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CapacidadTecnologiaRepository {
    Flux<CapacidadTecnologiaEntity> findByTecnologiaId(Integer tecnologiaId);
    Mono<Boolean> existsRelacion(Integer tecnologiaId, Integer capacidadId);
    Mono<Long> countByCapacidadId(Integer capacidadId);
    Mono<CapacidadTecnologiaEntity> save(CapacidadTecnologiaEntity entity);
}
