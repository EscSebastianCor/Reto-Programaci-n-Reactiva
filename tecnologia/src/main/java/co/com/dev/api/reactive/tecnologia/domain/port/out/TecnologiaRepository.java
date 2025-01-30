package co.com.dev.api.reactive.tecnologia.domain.port.out;

import co.com.dev.api.reactive.tecnologia.domain.model.Tecnologia;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TecnologiaRepository {

    Mono<Tecnologia> save(Tecnologia tecnologia);
    Mono<Boolean> existsByNombre(String nombre);
    Flux<Tecnologia> findAll(Pageable pageable);
}
