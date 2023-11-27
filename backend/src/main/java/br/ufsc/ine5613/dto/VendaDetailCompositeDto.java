package br.ufsc.ine5613.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.val;

@Getter
@Setter
public class VendaDetailCompositeDto {
  private Long funcionarioId;
  private String funcionarioCpf;
  private String funcionarioNome;
  private String funcionarioSobrenome;
  private Long estabelecimentoId;
  private String estabelecimentoEndereco;
  private Long estabelecimentoUfId;
  private String funcionarioCargo;
  private Long clienteId;
  private String clienteCpf;
  private String clienteNome;
  private String clienteSobrenome;
  private List<VendaProdutoDetailCompositeDto> produtos;
  private LocalDateTime dataHoraVenda;

  public VendaDetailCompositeDto(
      Long funcionarioId,
      String funcionarioCpf,
      String funcionarioNome,
      String funcionarioSobrenome,
      Long estabelecimentoId,
      String estabelecimentoEndereco,
      Long estabelecimentoUfId,
      String funcionarioCargo,
      Long clienteId,
      String clienteCpf,
      String clienteNome,
      String clienteSobrenome,
      String dataHoraVenda,
      String produtos)
      throws JsonProcessingException {
    val om = new ObjectMapper();

    val parsedProdutos =
        om.readValue(produtos, new TypeReference<List<VendaProdutoDetailCompositeDto>>() {});

    this.funcionarioId = funcionarioId;
    this.funcionarioCpf = funcionarioCpf;
    this.funcionarioNome = funcionarioNome;
    this.funcionarioSobrenome = funcionarioSobrenome;
    this.estabelecimentoId = estabelecimentoId;
    this.estabelecimentoEndereco = estabelecimentoEndereco;
    this.estabelecimentoUfId = estabelecimentoUfId;
    this.funcionarioCargo = funcionarioCargo;
    this.clienteId = clienteId;
    this.clienteCpf = clienteCpf;
    this.clienteNome = clienteNome;
    this.clienteSobrenome = clienteSobrenome;
    this.dataHoraVenda = LocalDateTime.parse(dataHoraVenda, DateTimeFormatter.ISO_DATE_TIME);
    this.produtos = parsedProdutos.get(0) == null ? new ArrayList<>() : parsedProdutos;
  }
}
