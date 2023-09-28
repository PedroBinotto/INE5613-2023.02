package br.ufsc.ine5613.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_rl_venda_produto")
@IdClass(VendaProdutoId.class)
@SqlResultSetMapping(
    name = "VendaProdutoMapping",
    entities = @EntityResult(
        entityClass = VendaProduto.class,
        fields = {
            @FieldResult(name = "vendaId", column = "id_rl_venda_produto_v"),
            @FieldResult(name = "produtoId", column = "id_rl_venda_produto_p"),
            @FieldResult(name = "quantidade", column = "quantidade"),
        }
    )
)
public class VendaProduto {
    @Id
    Long vendaId;
    @Id
    Long produtoId;
    int quantidade;
}
