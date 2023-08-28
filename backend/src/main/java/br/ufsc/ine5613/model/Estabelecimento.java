package br.ufsc.ine5613.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_estabelecimento")
@SqlResultSetMapping(
    name = "EstabelecimentoMapping",
    entities = @EntityResult(
        entityClass = Estabelecimento.class,
        fields = {
            @FieldResult(name = "id", column = "id_estabelecimento"),
            @FieldResult(name = "endereco", column = "endereco_estabelecimento"),
            @FieldResult(name = "ufId", column = "id_uf_estabelecimento"),
        }
    )
)
@NamedNativeQuery(
    name = "getEstabelecimentos",
    query = "SELECT * FROM tb_estabelecimento",
    resultSetMapping = "EstabelecimentoMapping"
)
public class Estabelecimento {
    @Id
    @Column(name = "id_estabelecimento")
    Long id;

    @Column(name = "endereco_estabelecimento")
    String endereco;

    @Column(name = "id_uf_estabelecimento")
    Long ufId;
}
