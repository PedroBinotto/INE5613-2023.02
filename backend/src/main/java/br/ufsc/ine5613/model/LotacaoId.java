package br.ufsc.ine5613.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class LotacaoId implements Serializable {
  private Long funcionarioId;
  private Long cargoId;
  private Long estabelecimentoId;
}
