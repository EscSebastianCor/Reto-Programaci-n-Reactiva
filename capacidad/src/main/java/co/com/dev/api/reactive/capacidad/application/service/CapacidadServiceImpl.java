package co.com.dev.api.reactive.capacidad.application.service;

import co.com.dev.api.reactive.capacidad.domain.exception.CapacidadAlreadyExistsException;
import co.com.dev.api.reactive.capacidad.domain.exception.TecnologiasInvalidasException;
import co.com.dev.api.reactive.capacidad.domain.model.Capacidad;
import co.com.dev.api.reactive.capacidad.domain.port.in.CrearCapacidadUseCase;
import co.com.dev.api.reactive.capacidad.domain.port.in.ListarCapacidadUseCase;
import co.com.dev.api.reactive.capacidad.domain.port.out.CapacidadRepository;
import co.com.dev.api.reactive.capacidad.infraestrcture.adapter.output.client.TecnologiaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CapacidadServiceImpl implements CrearCapacidadUseCase, ListarCapacidadUseCase {

    private final CapacidadRepository capacidadRepository;
    private final TecnologiaClient tecnologiaClient;

    @Override
    public Mono<Capacidad> crearCapacidad(String nombre, String descripcion, Set<Integer> tecnologiasIds) {
        if (tecnologiasIds.size() < 3) {
            return Mono.error(new TecnologiasInvalidasException("Debe asociar al menos 3 tecnologías"));
        }
        if (tecnologiasIds.size() > 20) {
            return Mono.error(new TecnologiasInvalidasException("No puede asociar más de 20 tecnologías"));
        }
        if (tecnologiasIds.size() != Set.copyOf(tecnologiasIds).size()) {
            return Mono.error(new TecnologiasInvalidasException("No puede haber tecnologías repetidas"));
        }

        return tecnologiaClient.verificarTecnologiasExisten(tecnologiasIds)
                .flatMap(existenTodas -> {
                    if (!existenTodas) {
                        return Mono.error(new TecnologiasInvalidasException("Una o más tecnologías no existen"));
                    }

                    return capacidadRepository.existsByNombre(nombre)
                            .flatMap(exists -> {
                                if (exists) {
                                    return Mono.error(new CapacidadAlreadyExistsException(nombre));
                                }

                                Capacidad capacidad = new Capacidad(nombre, descripcion, tecnologiasIds);

                                // Guardar capacidad y luego registrar relaciones en `capacidad_tecnologia`
                                return capacidadRepository.save(capacidad)
                                        .flatMapMany(capacidadGuardada -> Flux.fromIterable(tecnologiasIds)
                                                .flatMap(tecnologiaId -> tecnologiaClient.registrarRelacion(Integer.valueOf(capacidadGuardada.getUniqueId()), tecnologiaId))
                                        ).then(Mono.just(capacidad)); // Devolver la capacidad después de guardar la relación
                            });
                });
    }


    @Override
    public Flux<Capacidad> listarCapacidades(int page, int size, String sortBy, String sortDirection) {
        Sort.Direction direction = Sort.Direction.fromString(sortDirection.toUpperCase());
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        return capacidadRepository.findAll(pageable)
                .flatMap(capacidad -> tecnologiaClient.getTecnologias(capacidad.getTecnologiasIds())
                        .collectList()
                        .map(tecnologias -> {
                            capacidad.setTecnologiasIds(Set.copyOf(tecnologias));
                            return capacidad;
                        }));
    }

}
