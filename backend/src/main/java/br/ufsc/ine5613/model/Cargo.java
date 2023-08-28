package br.ufsc.ine5613.model;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_cargo")
@SqlResultSetMapping(
    name = "CargoMapping",
    entities = @EntityResult(
        entityClass = Cargo.class,
        fields = {
            @FieldResult(name = "id", column = "id_cargo"),
            @FieldResult(name = "nome", column = "nome"),
            @FieldResult(name = "salario", column = "salario"),
        }
    )
)
public class Cargo {
    @Id
    Long id;
    String nome;
    Float salario;
}
