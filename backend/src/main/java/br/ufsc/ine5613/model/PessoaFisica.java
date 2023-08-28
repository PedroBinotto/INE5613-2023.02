package br.ufsc.ine5613.model;

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
public class PessoaFisica {
    @Id
    Long id;
    String cpf;
    String nome;
    String sobrenome;
}
