package co.com.dev.api.reactive.capacidad.domain.exception;

public class CapacidadAlreadyExistsException extends RuntimeException {

    public CapacidadAlreadyExistsException(String nombre) {
        super("Ya existe una capacidad con el nombre: " + nombre);
    }
}
