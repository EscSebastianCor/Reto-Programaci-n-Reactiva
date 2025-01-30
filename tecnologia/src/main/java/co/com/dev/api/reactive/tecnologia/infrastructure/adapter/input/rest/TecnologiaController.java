package co.com.dev.api.reactive.tecnologia.infrastructure.adapter.input.rest;

import co.com.dev.api.reactive.tecnologia.domain.port.in.CrearTecnologiaUserCase;
import co.com.dev.api.reactive.tecnologia.domain.port.in.ListarTecnologiaUserCase;
import co.com.dev.api.reactive.tecnologia.infrastructure.dto.TecnologiaRequest;
import co.com.dev.api.reactive.tecnologia.infrastructure.dto.TecnologiaResponse;
import co.com.dev.api.reactive.tecnologia.infrastructure.mapper.TecnologiaMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/tecnologias/")
@AllArgsConstructor
public class TecnologiaController {

    private final CrearTecnologiaUserCase crearTecnologiaUserCase;
    private final ListarTecnologiaUserCase listarTecnologiaUserCase;
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
}
