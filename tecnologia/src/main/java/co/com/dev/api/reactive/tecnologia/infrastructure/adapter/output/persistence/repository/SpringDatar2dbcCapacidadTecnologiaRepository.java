package co.com.dev.api.reactive.tecnologia.infrastructure.adapter.output.persistence.repository;

import co.com.dev.api.reactive.tecnologia.infrastructure.adapter.output.persistence.entity.CapacidadTecnologiaEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface SpringDatar2dbcCapacidadTecnologiaRepository extends R2dbcRepository<CapacidadTecnologiaEntity, Integer> {

    Flux<CapacidadTecnologiaEntity> findByIdTecnologia(Integer idTecnologia);
    Mono<Boolean> existsByIdTecnologiaAndIdCapacidad(Integer idTecnologia, Integer idCapacidad);
    Mono<Long> countByIdCapacidad(Integer idCapacidad);

}
