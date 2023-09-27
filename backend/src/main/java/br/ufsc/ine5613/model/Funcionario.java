package br.ufsc.ine5613.model;

import br.ufsc.ine5613.dto.FuncionarioDetailCompositeDto;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_funcionario")
@SqlResultSetMapping(
    name = "FuncionarioMapping",
    entities = @EntityResult(
        entityClass = Funcionario.class,
        fields = {
            @FieldResult(name = "id", column = "id_funcionario"),
            @FieldResult(name = "pessoaFisicaId", column = "id_pessoa_fisica_funcionario")
        }
    )
)
@SqlResultSetMapping(
    name = "FuncionarioDetailCompositeMapping",
    classes={
        @ConstructorResult(
            targetClass = FuncionarioDetailCompositeDto.class,
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
        name = "getFuncionarios",
        query = """
            SELECT id_funcionario AS id, cpf, nome, sobrenome
            FROM tb_funcionario
            JOIN tb_pessoa_fisica
            ON id_pessoa_fisica_funcionario = id_pessoa_fisica
            WHERE (:nomeFilter IS NULL OR UPPER(nome) IN (:nomeFilter))
            AND   (:sobrenomeFilter IS NULL OR UPPER(sobrenome) IN (:sobrenomeFilter))
            AND   (:cpfFilter IS NULL OR cpf IN (:cpfFilter))
        """,
        resultSetMapping = "FuncionarioDetailCompositeMapping"
)
@NamedNativeQuery(
        name = "getFuncionarioById",
        query = """
            SELECT id_funcionario AS id, cpf, nome, sobrenome
            FROM tb_funcionario
            JOIN tb_pessoa_fisica
            ON id_pessoa_fisica_funcionario = id_pessoa_fisica
            WHERE id_funcionario = :funcionarioId
            LIMIT 1
        """,
        resultSetMapping = "FuncionarioDetailCompositeMapping"
)
@NamedNativeQuery(
        name = "saveFuncionario",
        query = """
            INSERT INTO tb_funcionario (id_pessoa_fisica_funcionario) values (:pessoaFisicaId)
        """
)
@NamedNativeQuery(
        name = "deleteFuncionario",
        query = """
            DELETE FROM tb_funcionario WHERE id_funcionario = :funcionarioId
        """
)
public class Funcionario {
    @Id
    Long id;
    Long pessoaFisicaId;
}
