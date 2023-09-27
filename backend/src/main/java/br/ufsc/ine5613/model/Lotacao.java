package br.ufsc.ine5613.model;


import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_estabelecimento")
@IdClass(LotacaoId.class)
@SqlResultSetMapping(
    name = "LotacaoMapping",
    entities = @EntityResult(
        entityClass = Lotacao.class,
        fields = {
            @FieldResult(name = "funcionarioId", column = "id_lotacao_funcionario"),
            @FieldResult(name = "cargoId", column = "id_lotacao_estabelecimento"),
            @FieldResult(name = "estabelecimentoId", column = "id_lotacao_estabelecimento"),
        }
    )
)
public class Lotacao {
    @Id
    Long funcionarioId;
    @Id
    Long cargoId;
    @Id
    Long estabelecimentoId;

}

