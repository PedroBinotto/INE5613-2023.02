package br.ufsc.ine5613.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_cliente")
@SqlResultSetMapping(
    name = "ClienteMapping",
    entities = @EntityResult(
        entityClass = Cliente.class,
        fields = {
            @FieldResult(name = "id", column = "id_cliente"),
            @FieldResult(name = "pessoaFisicaId", column = "id_pessoa_fisica_cliente")
        }
    )
)
public class Cliente {
    @Id
    Long id;
    Long pessoaFisicaId;
}
