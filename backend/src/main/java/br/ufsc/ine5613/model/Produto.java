package br.ufsc.ine5613.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_produto")
@SqlResultSetMapping(
    name = "ProdutoMapping",
    entities =
        @EntityResult(
            entityClass = Produto.class,
            fields = {
              @FieldResult(name = "id", column = "id_produto"),
              @FieldResult(name = "nome", column = "nome"),
              @FieldResult(name = "valor", column = "valor")
            }))
@NamedNativeQuery(
    name = "getProdutos",
    query =
        """
            SELECT * FROM tb_produto
            WHERE :nomeFilter IS NULL
            OR UPPER(nome) IN (:nomeFilter)
    """,
    resultSetMapping = "ProdutoMapping")
@NamedNativeQuery(
    name = "getProdutoById",
    query =
        """
            SELECT * FROM tb_produto
            WHERE id_produto = :produtoId
            LIMIT 1
    """,
    resultSetMapping = "ProdutoMapping")
@NamedNativeQuery(
    name = "saveProduto",
    query =
        """
            INSERT INTO tb_produto (nome, valor)
            VALUES (:nome, :valor)
    """,
    resultSetMapping = "ProdutoMapping")
@NamedNativeQuery(
    name = "deleteProdutoById",
    query = """
            DELETE FROM tb_produto WHERE id_produto = :produtoId
    """,
    resultSetMapping = "ProdutoMapping")
public class Produto {
  @Id Long id;
  String nome;
  Float valor;
}
