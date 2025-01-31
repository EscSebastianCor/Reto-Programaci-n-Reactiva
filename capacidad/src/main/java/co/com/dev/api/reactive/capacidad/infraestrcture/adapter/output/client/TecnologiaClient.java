package co.com.dev.api.reactive.capacidad.infraestrcture.adapter.output.client;

import co.com.dev.api.reactive.capacidad.domain.model.CapacidadTecnologia;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TecnologiaClient {

    private final WebClient webClient;
    private final WebClient.Builder webClientBuilder;

    public TecnologiaClient(WebClient.Builder webClientBuilder, WebClient.Builder webClientBuilder1) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
        this.webClientBuilder = webClientBuilder1;
    }

    public Mono<Boolean> verificarTecnologiasExisten(Set<Integer> tecnologiasIds) {
        return webClient.post()
                .uri("/api/tecnologias/verificar")
                .bodyValue(tecnologiasIds)
                .retrieve()
                .bodyToMono(Boolean.class);
    }

    public Flux<Integer> getTecnologias(Set<Integer> tecnologiasIds) {
        if (tecnologiasIds == null || tecnologiasIds.isEmpty()) {
            return Flux.empty();
        }

        String ids = tecnologiasIds.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/tecnologias/batch")
                        .queryParam("ids", ids)
                        .build())
                .retrieve()
                .bodyToFlux(Integer.class);
    }

    public Mono<Void> registrarRelacion(Integer capacidadId, Integer tecnologiaId) {
        CapacidadTecnologia relacion = new CapacidadTecnologia(null, capacidadId, tecnologiaId);

        return webClientBuilder.build()
                .post()
                .uri("http://localhost:8080/api/tecnologias/relacionar")
                .bodyValue(relacion)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
