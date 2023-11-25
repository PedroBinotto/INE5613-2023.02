package br.ufsc.ine5613.dto;

import java.time.LocalDateTime;
import java.util.List;

public record VendaResponseDto(
        LotacaoResponseDto lotacao,
        ClienteDetailCompositeDto cliente,
        List<VendaProdutoResponseDto> produtos,
        LocalDateTime dataHoraVenda

) {
    public record VendaProdutoResponseDto(
            ProdutoResponseDto produto,
            Long quantidade
    ) {}
}
