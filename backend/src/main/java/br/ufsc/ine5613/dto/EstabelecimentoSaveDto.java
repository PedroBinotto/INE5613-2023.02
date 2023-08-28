package br.ufsc.ine5613.dto;

import jakarta.annotation.Nullable;

public record EstabelecimentoSaveDto(
       String endereco,
       Long ufId
) { }
