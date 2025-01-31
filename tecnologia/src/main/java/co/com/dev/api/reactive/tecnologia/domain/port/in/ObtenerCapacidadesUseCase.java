package co.com.dev.api.reactive.tecnologia.domain.port.in;

import reactor.core.publisher.Flux;

public interface ObtenerCapacidadesUseCase {
    Flux<Integer> obtenerCapacidadesIds(Integer tecnologiaId);
}
