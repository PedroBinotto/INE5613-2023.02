package br.ufsc.ine5613.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.val;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


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
        String produtos,
        LocalDateTime dataHoraVenda
    ) throws JsonProcessingException {
        val om = new ObjectMapper();
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
        this.dataHoraVenda = dataHoraVenda;

        val parsedProdutos = om.readValue(produtos, List.class);      // FIXME: Gambiarra
        this.produtos = parsedProdutos.get(0) == null
                ? new ArrayList<>()
                : parsedProdutos;
    }
}
