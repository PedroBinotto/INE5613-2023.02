package br.ufsc.ine5613.controller;

import br.ufsc.ine5613.dto.FuncionarioDetailCompositeDto;
import br.ufsc.ine5613.dto.FuncionarioSaveDto;
import br.ufsc.ine5613.query.FuncionarioQuery;
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
@RequestMapping("/api/funcionarios")
@RequiredArgsConstructor
@Tag(name = "funcionarios")
public class FuncionarioController {
  private final FuncionarioQuery funcionarioQuery;

  @GetMapping()
  @Operation(summary = "GET funcionarios", tags = "funcionarios")
  public ResponseEntity<List<FuncionarioDetailCompositeDto>> getFuncionarios(
      @RequestParam(required = false) Optional<String[]> nome,
      @RequestParam(required = false) Optional<String[]> sobrenome,
      @RequestParam(required = false) Optional<String[]> cpf) {
    try {

      val nomeFilter =
          nome.map(
                  value ->
                      Arrays.stream(value).map(String::toUpperCase).collect(Collectors.toList()))
              .orElseGet(ArrayList::new);
      val sobrenomeFilter =
          sobrenome
              .map(
                  strings ->
                      Arrays.stream(strings).map(String::toUpperCase).collect(Collectors.toList()))
              .orElseGet(ArrayList::new);
      val cpfFilter = cpf.map(Arrays::asList).orElseGet(() -> new ArrayList<>() {});
      return ResponseEntity.ok(
          this.funcionarioQuery.getFuncionarios(nomeFilter, sobrenomeFilter, cpfFilter));
    } catch (NullPointerException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @GetMapping("/{funcionarioId}")
  @Operation(summary = "GET funcionario BY ID", tags = "funcionarios")
  public ResponseEntity<FuncionarioDetailCompositeDto> getFuncionarioById(
      @PathVariable Long funcionarioId) {
    return ResponseEntity.ok(this.funcionarioQuery.getFuncionarioById(funcionarioId));
  }

  @PostMapping()
  @Operation(summary = "SAVE funcionario", tags = "funcionarios")
  public ResponseEntity<Void> saveFuncionario(@RequestBody FuncionarioSaveDto funcionario) {
    this.funcionarioQuery.saveFuncionario(funcionario);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{funcionarioId}")
  @Operation(summary = "DELETE funcionario BY ID", tags = "funcionarios")
  public ResponseEntity<Void> deleteFuncionarioById(@PathVariable Long funcionarioId) {
    this.funcionarioQuery.deleteFuncionarioById(funcionarioId);
    return ResponseEntity.ok().build();
  }
}
