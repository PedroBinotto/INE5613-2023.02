package br.ufsc.ine5613.model;


import jakarta.persistence.*;
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
    @Column(name = "id_lotacao_funcionario")
    Long funcionarioId;

    @Id
    @Column(name = "id_lotacao_cargo")
    Long cargoId;

    @Id
    @Column(name = "id_lotacao_estabelecimento")
    Long estabelecimentoId;

}

