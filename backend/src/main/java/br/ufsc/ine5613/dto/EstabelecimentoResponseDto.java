package br.ufsc.ine5613.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EstabelecimentoResponseDto {
  private Long id;
  private String endereco;
  private UfDto uf;
}
