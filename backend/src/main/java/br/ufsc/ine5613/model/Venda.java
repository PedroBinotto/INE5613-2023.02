package br.ufsc.ine5613.model;


import javax.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_venda")
@SqlResultSetMapping(
    name = "VendaMapping",
    entities = @EntityResult(
        entityClass = Venda.class,
        fields = {
            @FieldResult(name = "id", column = "id_venda"),
            @FieldResult(name = "funcionarioId", column = "id_venda_funcionario"),
            @FieldResult(name = "clienteId", column = "id_venda_cliente"),
            @FieldResult(name = "estabelecimentoId", column = "id_venda_estabelecimento"),
            @FieldResult(name = "cargoId", column = "id_venda_cargo"),
            @FieldResult(name = "dataHoraVenda", column = "data_hora_venda"),
        }
    )
)
public class Venda {
    @Id
    Long id;
    Long funcionarioId;
    Long clienteId;
    Long estabelecimentoId;
    Long cargoId;
    LocalDateTime dataHoraVenda;
}
