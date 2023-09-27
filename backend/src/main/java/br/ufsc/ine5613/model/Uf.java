package br.ufsc.ine5613.model;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_uf")
@SqlResultSetMapping(
    name = "UfMapping",
    entities = @EntityResult(
        entityClass = Uf.class,
        fields = {
            @FieldResult(name = "id", column = "id_uf"),
            @FieldResult(name = "nome", column = "nome"),
            @FieldResult(name = "sigla", column = "sigla"),
        }
    )
)
public class Uf {
    @Id
    Long id;
    String nome;
    String sigla;
}
