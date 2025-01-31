package co.com.dev.api.reactive.capacidad.infraestrcture.adapter.input.rest;

import co.com.dev.api.reactive.capacidad.domain.port.in.CrearCapacidadUseCase;
import co.com.dev.api.reactive.capacidad.domain.port.in.ListarCapacidadUseCase;
import co.com.dev.api.reactive.capacidad.infraestrcture.dto.CapacidadRequest;
import co.com.dev.api.reactive.capacidad.infraestrcture.dto.CapacidadResponse;
import co.com.dev.api.reactive.capacidad.infraestrcture.mapper.CapacidadMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/capacidades")
@RequiredArgsConstructor
public class CapacidadController {

    private final CrearCapacidadUseCase crearCapacidadUseCase;
    private final ListarCapacidadUseCase listarCapacidadUseCase;
    private final CapacidadMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CapacidadResponse> crearCapacidad(@Valid @RequestBody CapacidadRequest request) {
        return crearCapacidadUseCase.crearCapacidad(
                        request.getNombre(),
                        request.getDescripcion(),
                        request.getTecnologiasIds()
                )
                .map(mapper::toResponse);
    }

    @GetMapping
    public Flux<CapacidadResponse> listarCapacidades(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nombre") String sort,
            @RequestParam(defaultValue = "asc") String direction) {
        return listarCapacidadUseCase.listarCapacidades(page, size, sort, direction)
                .map(mapper::toResponse);
    }
}
