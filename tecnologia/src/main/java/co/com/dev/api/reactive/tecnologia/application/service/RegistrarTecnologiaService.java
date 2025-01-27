package co.com.dev.api.reactive.tecnologia.application.service;

import co.com.dev.api.reactive.tecnologia.domain.model.Tecnologia;
import co.com.dev.api.reactive.tecnologia.domain.port.in.RegistrarTecnologiaUserCase;
import co.com.dev.api.reactive.tecnologia.domain.port.out.TecnologiaRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RegistrarTecnologiaService implements RegistrarTecnologiaUserCase {

    private final TecnologiaRepositoryPort tecnologiaRepository;

    @Override
    public Mono<Tecnologia> registrarTecnologia(String nombre, String descripcion) {
        return tecnologiaRepository.existsByNombre(nombre)
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new IllegalArgumentException("El nombre de la tecnología ya existe"));
                    }
                    Tecnologia tecnologia = new Tecnologia(null, nombre, descripcion);
                    return tecnologiaRepository.save(tecnologia);
                });
    }
}
