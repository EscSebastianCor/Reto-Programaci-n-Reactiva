package co.com.dev.api.reactive.tecnologia.application.service;

import co.com.dev.api.reactive.tecnologia.domain.exception.TechnologyAlreadyExistsException;
import co.com.dev.api.reactive.tecnologia.domain.model.Tecnologia;
import co.com.dev.api.reactive.tecnologia.domain.port.in.CrearTecnologiaUserCase;
import co.com.dev.api.reactive.tecnologia.domain.port.in.ListarTecnologiaUserCase;
import co.com.dev.api.reactive.tecnologia.domain.port.out.TecnologiaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class tecnologiaServiceImpl implements CrearTecnologiaUserCase, ListarTecnologiaUserCase {

    private final TecnologiaRepository repository;

    @Override
    public Mono<Tecnologia> crearTecnologia(String nombre, String descripcion) {
        return repository.existsByNombre(nombre)
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new TechnologyAlreadyExistsException("El nombre de la tecnología ya existe"));
                    }
                    Tecnologia tecnologia = new Tecnologia(null, nombre, descripcion, LocalDateTime.now(), LocalDateTime.now());
                    return repository.save(tecnologia);
                });
    }

    @Override
    public Flux<Tecnologia> listarTecnologias(int page, int size, String sortBy, String sortDirection) {
        Sort.Direction direction = Sort.Direction.fromString(sortDirection.toUpperCase());
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return repository.findAll(pageable);
    }
}
