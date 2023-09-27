package br.ufsc.ine5613.model;

import javax.persistence.*;
import lombok.Data;

import br.ufsc.ine5613.dto.ClienteDetailCompositeDto;

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
@SqlResultSetMapping(
        name = "ClienteDetailCompositeMapping",
        classes={
                @ConstructorResult(
                        targetClass = ClienteDetailCompositeDto.class,
                        columns={
                                @ColumnResult(name="id"),
                                @ColumnResult(name="cpf"),
                                @ColumnResult(name="nome"),
                                @ColumnResult(name="sobrenome")
                        }
                )
        }
)
@NamedNativeQuery(
        name = "getClientes",
        query = """
            SELECT id_cliente AS id, cpf, nome, sobrenome
            FROM tb_cliente
            JOIN tb_pessoa_fisica
            ON id_pessoa_fisica_cliente = id_pessoa_fisica
            WHERE (:nomeFilter IS NULL OR UPPER(nome) IN (:nomeFilter))
            AND   (:sobrenomeFilter IS NULL OR UPPER(sobrenome) IN (:sobrenomeFilter))
            AND   (:cpfFilter IS NULL OR cpf IN (:cpfFilter))
        """,
        resultSetMapping = "ClienteDetailCompositeMapping"
)
@NamedNativeQuery(
        name = "getClienteById",
        query = """
            SELECT id_cliente AS id, cpf, nome, sobrenome
            FROM tb_cliente
            JOIN tb_pessoa_fisica
            ON id_pessoa_fisica_cliente = id_pessoa_fisica
            WHERE id_cliente = :cliente
            LIMIT 1
        """,
        resultSetMapping = "ClienteDetailCompositeMapping"
)
@NamedNativeQuery(
        name = "saveCliente",
        query = """
            INSERT INTO tb_cliente (id_pessoa_fisica_cliente) values (:pessoaFisicaId)
        """
)
@NamedNativeQuery(
        name = "deleteCliente",
        query = """
            DELETE FROM tb_cliente WHERE id_cliente = :clienteId
        """
)
public class Cliente {
    @Id
    Long id;
    Long pessoaFisicaId;
}
