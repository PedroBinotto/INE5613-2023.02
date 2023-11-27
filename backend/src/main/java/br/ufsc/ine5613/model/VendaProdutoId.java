package br.ufsc.ine5613.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class VendaProdutoId implements Serializable {
  Long vendaId;
  Long produtoId;
}
