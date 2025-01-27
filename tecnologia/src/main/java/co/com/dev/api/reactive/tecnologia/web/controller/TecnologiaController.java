package co.com.dev.api.reactive.tecnologia.web.controller;



import co.com.dev.api.reactive.tecnologia.application.service.RegistrarTecnologiaService;
import co.com.dev.api.reactive.tecnologia.domain.model.Tecnologia;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/tecnologias")
@AllArgsConstructor
public class TecnologiaController {

    private final RegistrarTecnologiaService registrarTecnologiaService;

    @PostMapping
    public Mono<ResponseEntity<Tecnologia>> registrarTecnologia(@RequestBody @Valid Tecnologia tecnologia) {
        return registrarTecnologiaService.registrarTecnologia(tecnologia.getNombre(), tecnologia.getDescripcion())
                .map(ResponseEntity::ok);
    }
}
