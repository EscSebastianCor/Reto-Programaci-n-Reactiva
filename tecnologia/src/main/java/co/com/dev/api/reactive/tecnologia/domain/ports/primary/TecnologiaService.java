package co.com.dev.api.reactive.tecnologia.domain.ports.primary;

import co.com.dev.api.reactive.tecnologia.domain.model.Tecnologia;
import reactor.core.publisher.Mono;

public interface TecnologiaService {
    Mono<Tecnologia> registrarTecnologia(Tecnologia tecnologia);
}
