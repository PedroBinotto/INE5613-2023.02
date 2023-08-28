package br.ufsc.ine5613.model;

import jakarta.persistence.*;
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
                        @FieldResult(name = "pessoaFisicaId", column = "id_telefone_pessoa_fisica"),
                }
        )
)
public class Telefone {
    @Id
    @Column(name = "id_telefone")
    Long id;

    @Column(name = "num_telefone")
    String numeroTelefone;

    @Column(name = "id_telefone_pessoa_fisica")
    Long pessoaFisicaId;
}
