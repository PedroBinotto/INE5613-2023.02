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
    query = """
            SELECT * FROM tb_estabelecimento 
            WHERE :ufFilter IS NULL 
            OR id_uf_estabelecimento IN (:ufFilter)
    """,
    resultSetMapping = "EstabelecimentoMapping"
)
@NamedNativeQuery(
        name = "getEstabelecimentoById",
        query = """
            SELECT * 
            FROM tb_estabelecimento 
            WHERE id_estabelecimento = :estabelecimentoId 
            LIMIT 1
        """,
        resultSetMapping = "EstabelecimentoMapping"
)
@NamedNativeQuery(
        name = "saveEstabelecimento",
        query = """
            INSERT INTO tb_estabelecimento (endereco_estabelecimento, id_uf_estabelecimento)
            VALUES (:endereco, :ufId)
        """
)
@NamedNativeQuery(
        name = "deleteEstabelecimentoById",
        query = """
            DELETE FROM tb_estabelecimento WHERE id_estabelecimento = :estabelecimentoId
        """
)
@NamedNativeQuery(
        name = "updateEstabelecimento",
        query = """
            UPDATE tb_estabelecimento 
            SET endereco_estabelecimento = :endereco
            WHERE id_estabelecimento = :estabelecimentoId 
        """
)
public class Estabelecimento {
    @Id
    Long id;
    String endereco;
    Long ufId;
}
