package br.ufsc.ine5613.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class VendaProdutoId implements Serializable {
    Long vendaId;
    Long produtoId;
}
