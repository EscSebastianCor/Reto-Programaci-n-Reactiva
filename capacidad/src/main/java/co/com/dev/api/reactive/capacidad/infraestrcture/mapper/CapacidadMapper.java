package co.com.dev.api.reactive.capacidad.infraestrcture.mapper;

import co.com.dev.api.reactive.capacidad.domain.model.Capacidad;
import co.com.dev.api.reactive.capacidad.infraestrcture.adapter.output.persistence.CapacidadEntity;
import co.com.dev.api.reactive.capacidad.infraestrcture.dto.CapacidadResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CapacidadMapper {
    @Mapping(target = "uniqueId", source = "uniqueId")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "descripcion", source = "descripcion")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    CapacidadEntity toEntity(Capacidad capacidad);

    @Mapping(target = "uniqueId", source = "uniqueId")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "descripcion", source = "descripcion")
    CapacidadResponse toResponse(Capacidad capacidad);

    @Mapping(target = "uniqueId", source = "uniqueId")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "descripcion", source = "descripcion")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    @Mapping(target = "tecnologiasIds", ignore = true)
    Capacidad toDomain(CapacidadEntity entity);
}
