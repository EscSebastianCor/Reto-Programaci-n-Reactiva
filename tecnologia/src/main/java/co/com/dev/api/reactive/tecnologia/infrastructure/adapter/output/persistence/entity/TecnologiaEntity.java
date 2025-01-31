package co.com.dev.api.reactive.tecnologia.infrastructure.adapter.output.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@Table("tecnologias")
public class TecnologiaEntity {

    @Column("UniqueID")
    @Id
    private Integer uniqueId;
    private String nombre;
    private String descripcion;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
