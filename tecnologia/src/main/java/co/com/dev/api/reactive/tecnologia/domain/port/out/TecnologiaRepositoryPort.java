package co.com.dev.api.reactive.tecnologia.domain.port.out;

import co.com.dev.api.reactive.tecnologia.domain.model.Tecnologia;
import reactor.core.publisher.Mono;

public interface TecnologiaRepositoryPort {

    Mono<Boolean> existsByNombre(String nombre);
    Mono<Tecnologia> save(Tecnologia tecnologia);

}
