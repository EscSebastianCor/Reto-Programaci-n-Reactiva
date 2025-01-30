package co.com.dev.api.reactive.tecnologia.domain.exception;

public class TechnologyAlreadyExistsException extends RuntimeException{

    public TechnologyAlreadyExistsException(String nombre) {
        super("Ya existe una tecnología con el nombre: " + nombre);
    }
}
