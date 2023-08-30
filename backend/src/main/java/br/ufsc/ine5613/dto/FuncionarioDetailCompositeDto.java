package br.ufsc.ine5613.dto;

public record FuncionarioDetailCompositeDto (
        Long id,
        String cpf,
        String nome,
        String sobrenome
) { }
