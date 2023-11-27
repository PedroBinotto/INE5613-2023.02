package br.ufsc.ine5613.model;

import br.ufsc.ine5613.dto.LotacaoDetailCompositeDto;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_estabelecimento")
@IdClass(LotacaoId.class)
@SqlResultSetMapping(
    name = "LotacaoMapping",
    entities =
        @EntityResult(
            entityClass = Lotacao.class,
            fields = {
              @FieldResult(name = "funcionarioId", column = "id_lotacao_funcionario"),
              @FieldResult(name = "cargoId", column = "id_lotacao_estabelecimento"),
              @FieldResult(name = "estabelecimentoId", column = "id_lotacao_estabelecimento"),
            }))
@SqlResultSetMapping(
    name = "LotacaoDetailCompositeMapping",
    classes = {
      @ConstructorResult(
          targetClass = LotacaoDetailCompositeDto.class,
          columns = {
            @ColumnResult(name = "funcionarioId"),
            @ColumnResult(name = "funcionarioCpf"),
            @ColumnResult(name = "funcionarioNome"),
            @ColumnResult(name = "funcionarioSobrenome"),
            @ColumnResult(name = "estabelecimentoId"),
            @ColumnResult(name = "estabelecimentoEndereco"),
            @ColumnResult(name = "estabelecimentoUfId"),
            @ColumnResult(name = "funcionarioCargo")
          })
    })
@NamedNativeQuery(
    name = "getLotacoes",
    query =
        """
            SELECT
            id_funcionario AS funcionarioId,
            p.cpf AS funcionarioCpf,
            p.nome AS funcionarioNome,
            p.sobrenome AS funcionarioSobrenome,
            id_estabelecimento AS estabelecimentoId,
            endereco_estabelecimento AS estabelecimentoEndereco,
            id_uf_estabelecimento AS estabelecimentoUfId,
            c.nome AS funcionarioCargo
            FROM tb_lotacao l
            JOIN tb_funcionario f
            ON l.id_lotacao_funcionario = f.id_funcionario
            JOIN tb_pessoa_fisica p
            ON f.id_pessoa_fisica_funcionario = p.id_pessoa_fisica
            JOIN tb_cargo c
            ON l.id_lotacao_cargo = c.id_cargo
            JOIN tb_estabelecimento e
            ON l.id_lotacao_estabelecimento = e.id_estabelecimento
            WHERE (:funcionarioFilter IS NULL OR f.id_funcionario IN (:funcionarioFilter))
            AND   (:estabelecimentoFilter IS NULL OR e.id_estabelecimento IN (:estabelecimentoFilter))
            AND   (:cargoFilter IS NULL OR c.nome IN (:cargoFilter))
        """,
    resultSetMapping = "LotacaoDetailCompositeMapping")
@NamedNativeQuery(
    name = "getLotacaoById",
    query =
        """
            SELECT
            id_funcionario AS funcionarioId,
            p.cpf AS funcionarioCpf,
            p.nome AS funcionarioNome,
            p.sobrenome AS funcionarioSobrenome,
            id_estabelecimento AS estabelecimentoId,
            endereco_estabelecimento AS estabelecimentoEndereco,
            id_uf_estabelecimento AS estabelecimentoUfId,
            c.nome AS funcionarioCargo
            FROM tb_lotacao l
            JOIN tb_funcionario f
            ON l.id_lotacao_funcionario = f.id_funcionario
            JOIN tb_pessoa_fisica p
            ON f.id_pessoa_fisica_funcionario = p.id_pessoa_fisica
            JOIN tb_cargo c
            ON l.id_lotacao_cargo = c.id_cargo
            JOIN tb_estabelecimento e
            ON l.id_lotacao_estabelecimento = e.id_estabelecimento
            WHERE f.id_funcionario = :funcionarioId
            AND   e.id_estabelecimento = :estabelecimentoId
            AND   c.id_cargo = :cargoId
            LIMIT 1
        """,
    resultSetMapping = "LotacaoDetailCompositeMapping")
@NamedNativeQuery(
    name = "saveLotacao",
    query =
        """
            INSERT INTO tb_lotacao (
            id_lotacao_funcionario,
            id_lotacao_estabelecimento,
            id_lotacao_cargo
            ) values (
            :funcionarioId,
            :estabelecimentoId,
            :cargoId
            )
        """)
@NamedNativeQuery(
    name = "deleteLotacao",
    query =
        """
            DELETE
            FROM tb_lotacao l
            WHERE l.id_lotacao_funcionario = :funcionarioId
            AND   l.id_lotacao_estabelecimento = :estabelecimentoId
            AND   l.id_lotacao_cargo = :cargoId
        """)
public class Lotacao {
  @Id Long funcionarioId;
  @Id Long cargoId;
  @Id Long estabelecimentoId;
}
