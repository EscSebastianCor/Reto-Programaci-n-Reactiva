package co.com.dev.api.reactive.tecnologia.infrastructure.repository;

import co.com.dev.api.reactive.tecnologia.domain.model.Tecnologia;
import co.com.dev.api.reactive.tecnologia.domain.port.out.TecnologiaRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@AllArgsConstructor
public class TecnologiaR2dbcAdapter implements TecnologiaRepositoryPort {

    private final TecnologiaR2dbcRepository repository;

    @Override
    public Mono<Boolean> existsByNombre(String nombre) {
        return repository.existsByNombre(nombre);
    }

    @Override
    public Mono<Tecnologia> save(Tecnologia tecnologia) {
        return repository.save(tecnologia);
    }
}
