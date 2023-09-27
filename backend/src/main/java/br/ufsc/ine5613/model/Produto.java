package br.ufsc.ine5613.model;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_produto")
@SqlResultSetMapping(
    name = "ProdutoMapping",
    entities = @EntityResult(
        entityClass = Produto.class,
        fields = {
            @FieldResult(name = "id", column = "id_produto"),
            @FieldResult(name = "nome", column = "nome"),
            @FieldResult(name = "valor", column = "valor")
        }
    )
)
public class Produto {
    @Id
    Long id;
    String nome;
    Float valor;
}
