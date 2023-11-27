package br.ufsc.ine5613.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class VendaResponseDto {

  private LotacaoResponseDto lotacao;
  private ClienteDetailCompositeDto cliente;
  private List<VendaProdutoResponseDto> produtos;
  private LocalDateTime dataHoraVenda;

  public record VendaProdutoResponseDto(ProdutoResponseDto produto, Long quantidade) {}
}
