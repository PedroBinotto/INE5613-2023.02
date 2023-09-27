package br.ufsc.ine5613.model;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_telefone")
@SqlResultSetMapping(
    name = "TelefoneMapping",
    entities = @EntityResult(
        entityClass = Telefone.class,
        fields = {
            @FieldResult(name = "id", column = "id_telefone"),
            @FieldResult(name = "numeroTelefone", column = "num_telefone"),
            @FieldResult(name = "pessoaFisicaId", column = "id_pessoa_fisica_telefone"),
        }
    )
)
@NamedNativeQuery(
        name = "getTelefonesByPessoaFisicaId",
        query = """
            SELECT id_telefone, num_telefone, id_pessoa_fisica_telefone
            FROM tb_telefone
            JOIN tb_pessoa_fisica
            ON id_pessoa_fisica_telefone = id_pessoa_fisica
            WHERE id_pessoa_fisica = :pessoaFisicaId
        """,
        resultSetMapping = "TelefoneMapping"
)
@NamedNativeQuery(
        name = "saveTelefone",
        query = """
            INSERT INTO tb_telefone (id_pessoa_fisica_telefone, num_telefone)
            VALUES (:pessoaFisicaId, :telefone)
        """
)
@NamedNativeQuery(
        name = "deleteTelefone",
        query = """
            DELETE FROM tb_telefone WHERE id_pessoa_fisica_telefone = :pessoaFisicaId
            AND id_telefone = :telefoneId
        """
)
public class Telefone {
    @Id
    Long id;
    String numeroTelefone;
    Long pessoaFisicaId;
}
