package co.com.dev.api.reactive.capacidad.infraestrcture.adapter.output.persistence;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@Table("capacidades")
public class CapacidadEntity {
    @Id
    @Column("UniqueID")
    private String uniqueId;
    private String nombre;
    private String descripcion;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}