package co.com.dev.api.reactive.tecnologia.infrastructure.adapter.input.rest;

import co.com.dev.api.reactive.tecnologia.application.service.TecnologiaServiceImpl;
import co.com.dev.api.reactive.tecnologia.domain.model.CapacidadTecnologia;
import co.com.dev.api.reactive.tecnologia.domain.model.Tecnologia;
import co.com.dev.api.reactive.tecnologia.domain.port.in.CrearTecnologiaUserCase;
import co.com.dev.api.reactive.tecnologia.domain.port.in.ListarTecnologiaUserCase;
import co.com.dev.api.reactive.tecnologia.domain.port.out.CapacidadTecnologiaRepository;
import co.com.dev.api.reactive.tecnologia.domain.port.out.TecnologiaRepository;
import co.com.dev.api.reactive.tecnologia.infrastructure.adapter.output.persistence.entity.CapacidadTecnologiaEntity;
import co.com.dev.api.reactive.tecnologia.infrastructure.dto.TecnologiaRequest;
import co.com.dev.api.reactive.tecnologia.infrastructure.dto.TecnologiaResponse;
import co.com.dev.api.reactive.tecnologia.infrastructure.mapper.TecnologiaMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/tecnologias/")
@AllArgsConstructor
public class TecnologiaController {

    private final CrearTecnologiaUserCase crearTecnologiaUserCase;
    private final ListarTecnologiaUserCase listarTecnologiaUserCase;
    private final TecnologiaRepository tecnologiaRepository;
    private final TecnologiaServiceImpl tecnologiaService;
    private final CapacidadTecnologiaRepository capacidadTecnologiaRepository;

    @Qualifier("tecnologiaMapperImpl")
    private final TecnologiaMapper mapper;

    @PostMapping("crearTecnologia")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<TecnologiaResponse> crearTecnologia(@Valid @RequestBody TecnologiaRequest request) {
        return crearTecnologiaUserCase.crearTecnologia(request.getNombre(), request.getDescripcion())
                .map(mapper::toResponse);
    }

    @GetMapping("listarTecnologias")
    public Flux<TecnologiaResponse> listarTecnologias(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size,
                                                      @RequestParam(defaultValue = "nombre") String sort,
                                                      @RequestParam(defaultValue = "asc") String direction) {
        return listarTecnologiaUserCase.listarTecnologias(page, size, sort, direction)
                .map(mapper::toResponse);
    }

    @PostMapping("{tecnologiaId}/capacidades/{capacidadId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> asociarCapacidad(
            @PathVariable Integer tecnologiaId,
            @PathVariable Integer capacidadId) {
        return tecnologiaService.asociarCapacidad(tecnologiaId, capacidadId);
    }

    @GetMapping("{tecnologiaId}/capacidades")
    public Flux<Integer> obtenerCapacidades(@PathVariable Integer tecnologiaId) {
        return tecnologiaService.obtenerCapacidadesIds(tecnologiaId);
    }

    @PostMapping("verificar")
    public Mono<Boolean> verificarTecnologiasExisten(@RequestBody Set<Integer> tecnologiasIds) {
        return Flux.fromIterable(tecnologiasIds)
                .flatMap(id -> tecnologiaRepository.findById(id))
                .collectList()
                .map(tecnologias -> tecnologias.size() == tecnologiasIds.size());
    }

    @GetMapping("batch")
    public Flux<Integer> getTecnologiasBatch(@RequestParam(name = "ids") List<Integer> ids) {
        return Flux.fromIterable(ids)
                .flatMap(id -> tecnologiaRepository.findById(id))
                .map(Tecnologia::getUniqueId);
    }

    @PostMapping("relacionar")
    public Mono<ResponseEntity<CapacidadTecnologiaEntity>> registrarRelacion(@RequestBody CapacidadTecnologia relacion) {
        CapacidadTecnologiaEntity entity = new CapacidadTecnologiaEntity(
                relacion.getIdCapacidad(),
                relacion.getIdTecnologia()
        );

        return capacidadTecnologiaRepository.save(entity)
                .map(savedRelacion -> ResponseEntity.status(HttpStatus.CREATED).body(savedRelacion));
    }
}
