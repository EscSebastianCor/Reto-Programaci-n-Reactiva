package co.com.dev.api.reactive.tecnologia.infrastructure.adapter.output.persistence.repository;

import co.com.dev.api.reactive.tecnologia.domain.model.Tecnologia;
import co.com.dev.api.reactive.tecnologia.domain.port.out.TecnologiaRepository;
import co.com.dev.api.reactive.tecnologia.infrastructure.adapter.output.persistence.entity.TecnologiaEntity;
import co.com.dev.api.reactive.tecnologia.infrastructure.mapper.TecnologiaMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component

public class R2dbcTecnologiaRepository implements TecnologiaRepository {

    private final SpringDataR2dbcTechnologiaRepository repository;
    private final TecnologiaMapper mapper;

    public R2dbcTecnologiaRepository(
            SpringDataR2dbcTechnologiaRepository repository,
            @Qualifier("tecnologiaMapperImpl") TecnologiaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    @Override
    public Mono<Tecnologia> save(Tecnologia tecnologia) {
        TecnologiaEntity entity = mapper.toEntity(tecnologia);
        return repository.save(entity)
                .cast(TecnologiaEntity.class)
                .map(mapper::toDomain);
    }

    @Override
    public Mono<Boolean> existsByNombre(String nombre) {
        return repository.existsByNombre(nombre);
    }

    @Override
    public Flux<Tecnologia> findAll(Pageable pageable) {
        return repository.findAllBy(pageable)
                .map(mapper::toDomain);
    }

    @Override
    public Mono<Tecnologia> findById(Integer id) {
        return repository.findById(Long.valueOf(id))
                .map(mapper::toDomain);
    }
}
