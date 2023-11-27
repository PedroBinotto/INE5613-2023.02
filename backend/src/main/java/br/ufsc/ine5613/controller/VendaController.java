package br.ufsc.ine5613.controller;

import br.ufsc.ine5613.dto.LotacaoSaveDto;
import br.ufsc.ine5613.dto.VendaDetailCompositeDto;
import br.ufsc.ine5613.dto.VendaResponseDto;
import br.ufsc.ine5613.dto.VendaSaveDto;
import br.ufsc.ine5613.enums.UfEnum;
import br.ufsc.ine5613.mapper.VendaMapper;
import br.ufsc.ine5613.query.VendaQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/vendas")
@RequiredArgsConstructor
@Tag(name = "vendas")
public class VendaController {
    private final VendaQuery vendaQuery;
    private final VendaMapper vendaMapper;

    @GetMapping()
    @Operation(summary = "GET vendas", tags = "vendas")
    public ResponseEntity<List<VendaResponseDto>> getVendas(
            @RequestParam(required = false) Optional<Long[]> estabelecimentoId,
            @RequestParam(required = false) Optional<String[]> funcionarioCpf,
            @RequestParam(required = false) Optional<String[]> clienteCpf
//            @RequestParam(required = false) Optional<LocalDateTime> dataLimInferior,    // TODO
//            @RequestParam(required = false) Optional<LocalDateTime> dataLimSuperior
    ) {
        try {
            val funcionarioFilter = funcionarioCpf
                    .map(value -> Arrays.stream(value).collect(Collectors.toList()))
                    .orElseGet(ArrayList::new);
            val clienteFilter = clienteCpf
                    .map(value -> Arrays.stream(value).collect(Collectors.toList()))
                    .orElseGet(ArrayList::new);
            val estabelecimentoFilter = estabelecimentoId
                    .map(value -> Arrays.stream(value).collect(Collectors.toList()))
                    .orElseGet(ArrayList::new);

            return ResponseEntity.ok(this.vendaMapper.toResponseDto(this.vendaQuery.getVendas(
               funcionarioFilter,
               clienteFilter,
               estabelecimentoFilter
            )));
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping()
    @Operation(summary = "SAVE venda", tags = "vendas")
    public ResponseEntity<Void> saveVenda(@RequestBody VendaSaveDto venda) {
        val vendaId = this.vendaQuery.saveVenda(venda);
        venda.produtos().forEach(it -> this.vendaQuery.saveVendaProdutos(it, vendaId));
        return ResponseEntity.ok().build();
    }
}
