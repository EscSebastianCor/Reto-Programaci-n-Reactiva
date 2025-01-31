package co.com.dev.api.reactive.tecnologia.infrastructure.adapter.output.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("capacidad_tecnologias")
@Getter
@Setter
public class CapacidadTecnologiaEntity {

    @Id
    @Column("UniqueID")
    private Integer uniqueId;

    @Column("id_tecnologia") // CORREGIDO
    private Integer idTecnologia;

    @Column("id_capacidad")
    private Integer idCapacidad;

    // Constructor con asignación de valores
    public CapacidadTecnologiaEntity(Integer idCapacidad, Integer idTecnologia) {
        this.idCapacidad = idCapacidad;
        this.idTecnologia = idTecnologia;
    }

    public CapacidadTecnologiaEntity() {
    }
}

