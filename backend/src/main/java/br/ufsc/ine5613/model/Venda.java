package br.ufsc.ine5613.model;

import br.ufsc.ine5613.dto.VendaDetailCompositeDto;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_venda")
@SqlResultSetMapping(
    name = "VendaMapping",
    entities =
        @EntityResult(
            entityClass = Venda.class,
            fields = {
              @FieldResult(name = "id", column = "id_venda"),
              @FieldResult(name = "funcionarioId", column = "id_venda_funcionario"),
              @FieldResult(name = "clienteId", column = "id_venda_cliente"),
              @FieldResult(name = "estabelecimentoId", column = "id_venda_estabelecimento"),
              @FieldResult(name = "cargoId", column = "id_venda_cargo"),
              @FieldResult(name = "dataHoraVenda", column = "data_hora_venda"),
            }))
@SqlResultSetMapping(
    name = "VendaDetailCompositeMapping",
    classes = {
      @ConstructorResult(
          targetClass = VendaDetailCompositeDto.class,
          columns = {
            @ColumnResult(name = "funcionarioId"),
            @ColumnResult(name = "funcionarioCpf"),
            @ColumnResult(name = "funcionarioNome"),
            @ColumnResult(name = "funcionarioSobrenome"),
            @ColumnResult(name = "estabelecimentoId"),
            @ColumnResult(name = "estabelecimentoEndereco"),
            @ColumnResult(name = "estabelecimentoUfId"),
            @ColumnResult(name = "funcionarioCargo"),
            @ColumnResult(name = "clienteId"),
            @ColumnResult(name = "clienteCpf"),
            @ColumnResult(name = "clienteNome"),
            @ColumnResult(name = "clienteSobrenome"),
            @ColumnResult(name = "dataHoraVenda"),
            @ColumnResult(name = "produtos")
          })
    })
@NamedNativeQuery(
    name = "getVendas",
    query =
        """
            SELECT
            fc.id_funcionario AS funcionarioId,
            fc.cpf AS funcionarioCpf,
            fc.nome AS funcionarioNome,
            fc.sobrenome AS funcionarioSobrenome,
            e.id_estabelecimento AS estabelecimentoId,
            e.endereco_estabelecimento AS estabelecimentoEndereco,
            e.id_uf_estabelecimento estabelecimentoUfId,
            c.nome AS funcionarioCargo,
            cc.id_cliente AS clienteId,
            cc.cpf AS clienteCpf,
            cc.nome AS clienteNome,
            cc.sobrenome AS clienteSobrenome,
            to_json(v.data_hora_venda) #>> '{}' AS dataHoraVenda,
            json_agg(
                json_build_object(
                    'id', COALESCE(p.id_produto, 0),
                    'nome', COALESCE(p.nome, ''),
                    'valor', COALESCE(p.valor, 0),
                    'quantidade', COALESCE(rl.quantidade, 1)
                )) as produtos
            FROM tb_venda v
            JOIN (
                SELECT id_funcionario, cpf, nome, sobrenome
                FROM tb_funcionario
                JOIN tb_pessoa_fisica
                ON tb_pessoa_fisica.id_pessoa_fisica = tb_funcionario.id_pessoa_fisica_funcionario
            ) fc ON fc.id_funcionario = v.id_venda_funcionario
            JOIN (
                SELECT id_cliente, cpf, nome, sobrenome
                FROM tb_cliente
                JOIN tb_pessoa_fisica
                ON tb_pessoa_fisica.id_pessoa_fisica = tb_cliente.id_pessoa_fisica_cliente
            ) cc ON cc.id_cliente = v.id_venda_cliente
            JOIN tb_estabelecimento e ON v.id_venda_estabelecimento = e.id_estabelecimento
            JOIN tb_cargo c on v.id_venda_cargo = c.id_cargo
            JOIN tb_rl_venda_produto rl on rl.id_rl_venda_produto_v = v.id_venda
            JOIN tb_produto p on rl.id_rl_venda_produto_p = p.id_produto
            WHERE (:clienteFilter IS NULL OR cc.cpf IN (:clienteFilter))
            AND   (:funcionarioFilter IS NULL OR fc.cpf IN (:funcionarioFilter))
            AND   (:estabelecimentoFilter IS NULL OR e.id_estabelecimento IN (:estabelecimentoFilter))
            AND   (CAST(:dataLimInferiorFilter AS DATE) IS NULL OR v.data_hora_venda >= CAST(:dataLimInferiorFilter AS DATE))
            AND   (CAST(:dataLimSuperiorFilter AS DATE) IS NULL OR v.data_hora_venda < CAST(:dataLimSuperiorFilter AS DATE))
            GROUP BY
                v.id_venda_cargo,
                v.data_hora_venda,
                cc.id_cliente,
                fc.cpf,
                fc.nome,
                fc.sobrenome,
                e.id_estabelecimento,
                e.endereco_estabelecimento,
                e.id_uf_estabelecimento,
                c.nome,
                fc.id_funcionario,
                cc.cpf,
                cc.nome,
                cc.sobrenome
        """,
    resultSetMapping = "VendaDetailCompositeMapping")
@NamedNativeQuery(
    name = "saveVenda",
    query =
        """
            INSERT INTO tb_venda (
                id_venda_funcionario,
                id_venda_estabelecimento,
                id_venda_cargo,
                id_venda_cliente,
                data_hora_venda
            ) VALUES (
                :funcionarioId,
                :estabelecimentoId,
                :cargoId,
                :clienteId,
                NOW()
            ) RETURNING id_venda
        """)
@NamedNativeQuery(
    name = "saveVendaProdutos",
    query =
        """
            INSERT INTO tb_rl_venda_produto (
                id_rl_venda_produto_p,
                id_rl_venda_produto_v,
                quantidade
            ) VALUES (
                :produtoId,
                :vendaId,
                :quantidade
            )
        """)
public class Venda {
  @Id Long id;
  Long funcionarioId;
  Long clienteId;
  Long estabelecimentoId;
  Long cargoId;
  LocalDateTime dataHoraVenda;
}
