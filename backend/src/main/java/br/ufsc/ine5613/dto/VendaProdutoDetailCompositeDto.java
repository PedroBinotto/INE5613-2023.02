package br.ufsc.ine5613.dto;

public record VendaProdutoDetailCompositeDto(
        Long id,
        String nome,
        Float valor,
        Long quantidade
) {
}
