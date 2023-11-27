package br.ufsc.ine5613.dto;

import br.ufsc.ine5613.enums.CargoEnum;

import java.util.List;

public record VendaSaveDto(
    Long clienteId,
    Long funcionarioId,
    Long estabelecimentoId,
    CargoEnum cargo,
    List<VendaProdutoSaveDto> produtos
) {
    public record VendaProdutoSaveDto(
              Long produtoId,
              Long quantidade
    ) {}
}
