package br.ufsc.ine5613.dto;

import lombok.Data;

@Data
public class EstabelecimentoResponseDto {
    private Long id;
    private String endereco;
    private UfDto uf;
}