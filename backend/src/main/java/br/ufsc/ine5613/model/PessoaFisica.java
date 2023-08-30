package br.ufsc.ine5613.model;

import br.ufsc.ine5613.dto.PessoaFisicaDetailCompositeDto;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_pessoa_fisica")
@SqlResultSetMapping(
    name = "PessoaFisicaMapping",
    entities = @EntityResult(
        entityClass = PessoaFisica.class,
        fields = {
            @FieldResult(name = "id", column = "id_pessoa_fisica"),
            @FieldResult(name = "cpf", column = "cpf"),
            @FieldResult(name = "nome", column = "nome"),
            @FieldResult(name = "sobrenome", column = "sobrenome"),
        }
    )
)
@SqlResultSetMapping(
    name = "PessoaFisicaDetailCompositeMapping",
        classes={
            @ConstructorResult(
            targetClass = PessoaFisicaDetailCompositeDto.class,
            columns={
                @ColumnResult(name="id"),
                @ColumnResult(name="cpf"),
                @ColumnResult(name="nome"),
                @ColumnResult(name="sobrenome"),
                @ColumnResult(name="telefones")
            }
        )
    }
)
@NamedNativeQuery(
        name = "getPessoasFisicas",
        query = """
            SELECT * FROM tb_pessoa_fisica
            WHERE (:nomeFilter IS NULL OR UPPER(nome) IN (:nomeFilter))
            AND   (:sobrenomeFilter IS NULL OR UPPER(sobrenome) IN (:sobrenomeFilter))
    """,
        resultSetMapping = "PessoaFisicaMapping"
)
@NamedNativeQuery(
        name = "getPessoaFisicaById",
        query = """
            SELECT id_pessoa_fisica as id, cpf, nome, sobrenome, array_to_json(array_agg(num_telefone)) as telefones
            FROM tb_pessoa_fisica
            LEFT JOIN tb_telefone
            ON id_pessoa_fisica_telefone = id_pessoa_fisica
            WHERE id_pessoa_fisica = :pessoaFisicaId
            GROUP BY id_pessoa_fisica
            LIMIT 1
        """,
        resultSetMapping = "PessoaFisicaDetailCompositeMapping"
)
@NamedNativeQuery(
        name = "savePessoaFisica",
        query = """
            INSERT INTO tb_pessoa_fisica (cpf, nome, sobrenome)
            VALUES (:cpf, :nome, :sobrenome)
        """
)
@NamedNativeQuery(
        name = "deletePessoaFisicaById",
        query = """
            DELETE FROM tb_pessoa_fisica WHERE id_pessoa_fisica = :pessoaFisicaId
        """
)
@NamedNativeQuery(
        name = "updatePessoaFisica",
        query = """
            UPDATE tb_pessoa_fisica
            SET nome = :nome, sobrenome = :sobrenome
            WHERE id_pessoa_fisica = :pessoaFisicaId
        """
)
public class PessoaFisica {
    @Id
    Long id;
    String cpf;
    String nome;
    String sobrenome;
}
