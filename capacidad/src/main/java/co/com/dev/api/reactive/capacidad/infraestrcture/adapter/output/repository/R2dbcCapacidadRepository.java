package co.com.dev.api.reactive.capacidad.infraestrcture.adapter.output.repository;

import co.com.dev.api.reactive.capacidad.domain.model.Capacidad;
import co.com.dev.api.reactive.capacidad.domain.port.out.CapacidadRepository;
import co.com.dev.api.reactive.capacidad.infraestrcture.mapper.CapacidadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
@RequiredArgsConstructor
public class R2dbcCapacidadRepository implements CapacidadRepository {

    private final SpringDataR2dbcCapacidadRepository repository;
    private final CapacidadMapper mapper;

    @Override
    public Mono<Capacidad> save(Capacidad capacidad) {
        return Mono.just(capacidad)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(mapper::toDomain);
    }

    @Override
    public Mono<Boolean> existsByNombre(String nombre) {
        return repository.existsByNombre(nombre);
    }

    @Override
    public Flux<Capacidad> findAll(Pageable pageable) {
        return repository.findAllBy(pageable)
                .map(mapper::toDomain);
    }
}
