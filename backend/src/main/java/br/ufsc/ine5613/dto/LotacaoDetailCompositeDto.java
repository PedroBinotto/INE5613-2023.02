package br.ufsc.ine5613.dto;

public record LotacaoDetailCompositeDto(
        Long funcionarioId,
        String funcionarioCpf,
        String funcionarioNome,
        String funcionarioSobrenome,
        Long estabelecimentoId,
        String estabelecimentoEndereco,
        Long estabelecimentoUfId,
        String funcionarioCargo
) { }
