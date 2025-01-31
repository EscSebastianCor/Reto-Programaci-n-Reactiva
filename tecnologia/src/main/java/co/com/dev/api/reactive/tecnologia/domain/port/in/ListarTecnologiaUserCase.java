package co.com.dev.api.reactive.tecnologia.domain.port.in;

import co.com.dev.api.reactive.tecnologia.domain.model.Tecnologia;
import reactor.core.publisher.Flux;

public interface ListarTecnologiaUserCase {
    Flux<Tecnologia> listarTecnologias(int page, int size, String sortBy, String sortDirection);
}
