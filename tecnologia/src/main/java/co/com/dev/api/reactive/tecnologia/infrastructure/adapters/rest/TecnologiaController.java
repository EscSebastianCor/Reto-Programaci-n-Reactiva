package co.com.dev.api.reactive.tecnologia.infrastructure.adapters.rest;

import co.com.dev.api.reactive.tecnologia.domain.model.Tecnologia;
import co.com.dev.api.reactive.tecnologia.domain.ports.primary.TecnologiaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/tecnologias")
@RequiredArgsConstructor
public class TecnologiaController {

    private final TecnologiaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Tecnologia> registrarTecnologia(@RequestBody Tecnologia tecnologia) {
        return service.registrarTecnologia(tecnologia);
    }
}
