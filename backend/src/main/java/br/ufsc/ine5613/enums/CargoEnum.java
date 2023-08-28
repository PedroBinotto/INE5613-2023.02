package br.ufsc.ine5613.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CargoEnum {
    GERENTE(1L, "GERENTE"),
    VENDEDOR(2L, "VENDEDOR");

    private Long id;
    private String nome;
}
