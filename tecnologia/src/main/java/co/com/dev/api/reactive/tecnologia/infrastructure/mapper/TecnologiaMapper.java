package co.com.dev.api.reactive.tecnologia.infrastructure.mapper;

import co.com.dev.api.reactive.tecnologia.domain.model.Tecnologia;
import co.com.dev.api.reactive.tecnologia.infrastructure.adapter.output.persistence.TecnologiaEntity;
import co.com.dev.api.reactive.tecnologia.infrastructure.dto.TecnologiaResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TecnologiaMapper {
    TecnologiaMapper INSTANCE = Mappers.getMapper(TecnologiaMapper.class);

    @Mapping(target = "uniqueId", source = "uniqueId")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "descripcion", source = "descripcion")
    TecnologiaResponse toResponse(Tecnologia tecnologia);

    @Mapping(target = "uniqueId", source = "uniqueId")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "descripcion", source = "descripcion")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    TecnologiaEntity toEntity(Tecnologia tecnologia);

    @Mapping(target = "uniqueId", source = "uniqueId")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "descripcion", source = "descripcion")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    Tecnologia toDomain(TecnologiaEntity entity);
}
