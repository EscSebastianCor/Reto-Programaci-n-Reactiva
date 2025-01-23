package co.com.dev.api.reactive.tecnologia.infrastructure.adapters.persistence;

import co.com.dev.api.reactive.tecnologia.domain.model.Tecnologia;
import co.com.dev.api.reactive.tecnologia.domain.ports.secondary.TecnologiaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class TecnologiaRepositoryAdapter implements TecnologiaRepository {

    private final R2dbcTecnologiaRepository r2dbcRepository;

    @Override
    public Mono<Tecnologia> save(Tecnologia tecnologia) {
        TecnologiaEntity entity = toEntity(tecnologia);
        return r2dbcRepository.save(entity)
                .map(this::toModel);
    }

    @Override
    public Mono<Boolean> existsByNombre(String nombre) {
        return r2dbcRepository.existsByNombre(nombre);
    }

    private TecnologiaEntity toEntity(Tecnologia tecnologia) {
        TecnologiaEntity entity = new TecnologiaEntity();
        entity.setNombre(tecnologia.getNombre());
        entity.setDescripcion(tecnologia.getDescripcion());
        return entity;
    }

    private  Tecnologia toModel(TecnologiaEntity entity) {
        return Tecnologia.builder()
                .id(entity.getUniqueId())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .build();
    }
}
