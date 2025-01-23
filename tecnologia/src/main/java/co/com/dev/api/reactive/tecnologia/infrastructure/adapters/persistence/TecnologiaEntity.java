package co.com.dev.api.reactive.tecnologia.infrastructure.adapters.persistence;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("TECNOLOGIAS")
public class TecnologiaEntity {

    @Id
    @Column("UniqueId")
    private Integer uniqueId;
    @Column("nombre")
    private String nombre;
    @Column("descripcion")
    private String descripcion;
}
