package co.com.dev.api.reactive.tecnologia.domain.port.in;

import reactor.core.publisher.Mono;

public interface AspciarCapacidadUseCase {

    Mono<Void> asociarCapacidad(Integer tecnologiaId, Integer capacidadId);
}
