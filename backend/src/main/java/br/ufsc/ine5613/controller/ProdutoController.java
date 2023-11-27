package br.ufsc.ine5613.controller;

import br.ufsc.ine5613.dto.ProdutoResponseDto;
import br.ufsc.ine5613.dto.ProdutoSaveDto;
import br.ufsc.ine5613.mapper.ProdutoMapper;
import br.ufsc.ine5613.query.ProdutoQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.*;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/produtos")
@RequiredArgsConstructor
@Tag(name = "produtos")
public class ProdutoController {
  private final ProdutoQuery produtoQuery;
  private final ProdutoMapper produtoMapper;

  @GetMapping()
  @Operation(summary = "GET produtos", tags = "produtos")
  public ResponseEntity<List<ProdutoResponseDto>> getProdutos(
      @RequestParam(required = false) Optional<String[]> nome) {
    try {
      val nomeFilter =
          nome.map(
                  value ->
                      Arrays.stream(value).map(String::toUpperCase).collect(Collectors.toList()))
              .orElseGet(ArrayList::new);

      return ResponseEntity.ok(this.produtoMapper.toDto(this.produtoQuery.getProdutos(nomeFilter)));
    } catch (NullPointerException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @GetMapping("/{produtoId}")
  @Operation(summary = "GET produto BY ID", tags = "produtos")
  public ResponseEntity<ProdutoResponseDto> getProdutoById(@PathVariable Long produtoId) {
    try {
      return ResponseEntity.ok(
          this.produtoMapper.toDto(this.produtoQuery.getProdutoById(produtoId)));
    } catch (NullPointerException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @PostMapping()
  @Operation(summary = "SAVE produto", tags = "produtos")
  public ResponseEntity<Void> saveProduto(@RequestBody ProdutoSaveDto produto) {
    this.produtoQuery.saveProduto(produto);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{produtoId}")
  @Operation(summary = "DELETE produto BY ID", tags = "produtos")
  public ResponseEntity<Void> deleteProdutoById(@PathVariable Long produtoId) {
    this.produtoQuery.deleteProdutoById(produtoId);
    return ResponseEntity.ok().build();
  }
}
