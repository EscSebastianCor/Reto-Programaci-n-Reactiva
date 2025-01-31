package co.com.dev.api.reactive.tecnologia.infrastructure.adapter.output.persistence.repository;

import co.com.dev.api.reactive.tecnologia.domain.port.out.CapacidadTecnologiaRepository;
import co.com.dev.api.reactive.tecnologia.infrastructure.adapter.output.persistence.entity.CapacidadTecnologiaEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class R2dbcCapacidadTecnologiaRepository implements CapacidadTecnologiaRepository {

    private final SpringDatar2dbcCapacidadTecnologiaRepository repository;

    public R2dbcCapacidadTecnologiaRepository(
            SpringDatar2dbcCapacidadTecnologiaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<CapacidadTecnologiaEntity> findByTecnologiaId(Integer tecnologiaId) {
        return repository.findByIdTecnologia(tecnologiaId);
    }

    @Override
    public Mono<Boolean> existsRelacion(Integer tecnologiaId, Integer capacidadId) {
        return repository.existsByIdTecnologiaAndIdCapacidad(tecnologiaId, capacidadId);
    }

    @Override
    public Mono<Long> countByCapacidadId(Integer capacidadId) {
        return repository.countByIdCapacidad(capacidadId);
    }

    @Override
    public Mono<CapacidadTecnologiaEntity> save(CapacidadTecnologiaEntity entity) {
        return repository.save(entity);
    }
}
