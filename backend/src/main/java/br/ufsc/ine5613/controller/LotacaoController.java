package br.ufsc.ine5613.controller;

import br.ufsc.ine5613.dto.LotacaoResponseDto;
import br.ufsc.ine5613.dto.LotacaoSaveDto;
import br.ufsc.ine5613.enums.CargoEnum;
import br.ufsc.ine5613.mapper.LotacaoMapper;
import br.ufsc.ine5613.query.LotacaoQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lotacoes")
@RequiredArgsConstructor
@Tag(name = "lotacoes")
public class LotacaoController {
  private final LotacaoQuery lotacaoQuery;
  private final LotacaoMapper lotacaoMapper;

  @GetMapping()
  @Operation(summary = "GET lotacoes", tags = "lotacoes")
  public ResponseEntity<List<LotacaoResponseDto>> getLotacoes(
      @RequestParam(required = false) Optional<Long[]> funcionarioId,
      @RequestParam(required = false) Optional<Long[]> estabelecimentoId,
      @RequestParam(required = false) Optional<CargoEnum[]> cargo) {
    try {
      val funcionarioFilter =
          funcionarioId
              .map(value -> Arrays.stream(value).collect(Collectors.toList()))
              .orElseGet(ArrayList::new);
      val estabelecimentoFilter =
          estabelecimentoId
              .map(value -> Arrays.stream(value).collect(Collectors.toList()))
              .orElseGet(ArrayList::new);
      val cargoFilter =
          cargo
              .map(
                  value ->
                      Arrays.stream(value).map(CargoEnum::getNome).collect(Collectors.toList()))
              .orElseGet(ArrayList::new);
      return ResponseEntity.ok(
          this.lotacaoMapper.toDto(
              this.lotacaoQuery.getLotacoes(
                  funcionarioFilter, estabelecimentoFilter, cargoFilter)));
    } catch (NullPointerException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @GetMapping("/funcionario/{funcionarioId}/estabelecimento/{estabelecimentoId}/cargo/{cargo}")
  @Operation(
      summary = "GET lotacao BY funcionario ID, estabelecimento ID, cargo",
      tags = "lotacoes")
  public ResponseEntity<LotacaoResponseDto> getLotacaoById(
      @PathVariable Long funcionarioId,
      @PathVariable Long estabelecimentoId,
      @PathVariable CargoEnum cargo) {
    try {
      return ResponseEntity.ok(
          this.lotacaoMapper.toDto(
              this.lotacaoQuery.getLotacaoById(funcionarioId, estabelecimentoId, cargo.getId())));
    } catch (NullPointerException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @PostMapping()
  @Operation(summary = "SAVE lotacao", tags = "lotacoes")
  public ResponseEntity<Void> saveLotacao(@RequestBody LotacaoSaveDto lotacao) {
    this.lotacaoQuery.saveLotacao(lotacao);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/funcionario/{funcionarioId}/estabelecimento/{estabelecimentoId}/cargo/{cargo}")
  @Operation(
      summary = "DELETE lotacao BY funcionario ID, estabelecimento ID, cargo",
      tags = "lotacoes")
  public ResponseEntity<Void> deleteLotacaoById(
      @PathVariable Long funcionarioId,
      @PathVariable Long estabelecimentoId,
      @PathVariable CargoEnum cargo) {
    this.lotacaoQuery.deleteLotacaoById(funcionarioId, estabelecimentoId, cargo.getId());
    return ResponseEntity.ok().build();
  }
}
