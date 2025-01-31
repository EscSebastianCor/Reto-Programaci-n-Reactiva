package co.com.dev.api.reactive.tecnologia.application.service;

import co.com.dev.api.reactive.tecnologia.domain.exception.MaximoTecnologiasException;
import co.com.dev.api.reactive.tecnologia.domain.exception.RelacionExistenteException;
import co.com.dev.api.reactive.tecnologia.domain.exception.TechnologyAlreadyExistsException;
import co.com.dev.api.reactive.tecnologia.domain.model.Tecnologia;
import co.com.dev.api.reactive.tecnologia.domain.port.in.CrearTecnologiaUserCase;
import co.com.dev.api.reactive.tecnologia.domain.port.in.ListarTecnologiaUserCase;
import co.com.dev.api.reactive.tecnologia.domain.port.out.CapacidadTecnologiaRepository;
import co.com.dev.api.reactive.tecnologia.domain.port.out.TecnologiaRepository;
import co.com.dev.api.reactive.tecnologia.infrastructure.adapter.output.persistence.entity.CapacidadTecnologiaEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class TecnologiaServiceImpl implements CrearTecnologiaUserCase, ListarTecnologiaUserCase {

    private final TecnologiaRepository repository;
    private final CapacidadTecnologiaRepository capacidadRepository;

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


    public Mono<Void> asociarCapacidad(Integer tecnologiaId, Integer capacidadId) {
        return capacidadRepository.existsRelacion(tecnologiaId, capacidadId)
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new RelacionExistenteException("La tecnología ya está asociada a esta capacidad"));
                    }

                    return capacidadRepository.countByCapacidadId(capacidadId)
                            .flatMap(count -> {
                                if (count >= 20) {
                                    return Mono.error(new MaximoTecnologiasException("La capacidad ya tiene el máximo de tecnologías permitidas (20)"));
                                }

                                CapacidadTecnologiaEntity entity = new CapacidadTecnologiaEntity(capacidadId, tecnologiaId);
                                return capacidadRepository.save(entity);
                            });
                })
                .then();
    }


    public Flux<Integer> obtenerCapacidadesIds(Integer tecnologiaId) {
        return capacidadRepository.findByTecnologiaId(tecnologiaId)
                .map(CapacidadTecnologiaEntity::getIdCapacidad);
    }
}
