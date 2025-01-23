package co.com.dev.api.reactive.tecnologia.application.service;

import co.com.dev.api.reactive.tecnologia.domain.model.Tecnologia;
import co.com.dev.api.reactive.tecnologia.domain.ports.primary.TecnologiaService;
import co.com.dev.api.reactive.tecnologia.domain.ports.secondary.TecnologiaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TecnologiaServiceImpl implements TecnologiaService {

    private final TecnologiaRepository repository;

    @Override
    public Mono<Tecnologia> registrarTecnologia(Tecnologia tecnologia) {
        return Mono.just(tecnologia)
                .filter(Tecnologia::esValido)
                .flatMap(tech -> repository.existsByNombre(tech.getNombre())
                        .filter(exists -> !exists)
                        .switchIfEmpty(Mono.error(new RuntimeException("Nombre de tecnología ya existe")))
                        .thenReturn(tech))
                .flatMap(repository::save)
                .switchIfEmpty(Mono.error(new RuntimeException("Datos de tecnología inválidos")));
    }
}
