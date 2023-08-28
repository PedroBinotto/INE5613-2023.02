package br.ufsc.ine5613.model;

import jakarta.persistence.*;
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
public class Funcionario {
    @Id
    @Column(name = "id_funcionario")
    Long id;

    @Column(name = "id_pessoa_fisica_funcionario")
    Long pessoaFisicaId;
}
