package br.ufsc.ine5613.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class LotacaoId implements Serializable {
    private Long funcionarioId;
    private Long cargoId;
    private Long estabelecimentoId;
}
