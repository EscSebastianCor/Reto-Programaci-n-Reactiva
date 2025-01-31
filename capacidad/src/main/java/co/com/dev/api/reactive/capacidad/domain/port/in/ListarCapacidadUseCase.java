package co.com.dev.api.reactive.capacidad.domain.port.in;

import co.com.dev.api.reactive.capacidad.domain.model.Capacidad;
import reactor.core.publisher.Flux;

public interface ListarCapacidadUseCase {
    Flux<Capacidad> listarCapacidades(int page, int size, String sortBy, String sortDirection);
}
