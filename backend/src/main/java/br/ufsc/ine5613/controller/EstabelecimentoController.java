package br.ufsc.ine5613.controller;

import br.ufsc.ine5613.dto.EstabelecimentoResponseDto;
import br.ufsc.ine5613.dto.EstabelecimentoSaveDto;
import br.ufsc.ine5613.enums.UfEnum;
import br.ufsc.ine5613.mapper.EstabelecimentoMapper;
import br.ufsc.ine5613.query.EstabelecimentoQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/estabelecimentos")
@RequiredArgsConstructor
@Tag(name = "estabelecimentos")
public class EstabelecimentoController {
    private final EstabelecimentoQuery estabelecimentoQuery;
    private final EstabelecimentoMapper estabelecimentoMapper;

    @GetMapping()
    @Operation(summary = "GET estabelecimentos", tags = "estabelecimentos")
    public ResponseEntity<List<EstabelecimentoResponseDto>> getEstabelecimentos(
            @RequestParam(required = false) Optional<UfEnum[]> uf
    ) {
        try {
            Long[] ufFilter = uf.map(ufEnums -> Arrays
                    .stream(ufEnums)
                    .map(UfEnum::getId)
                    .toList().toArray(new Long[]{})).orElseGet(() -> new Long[]{});
            return ResponseEntity.ok(
                this.estabelecimentoMapper
                    .toDto(this.estabelecimentoQuery.getEstabelecimentos(Arrays.stream(ufFilter).toList()))
            );
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{estabelecimentoId}")
    @Operation(summary = "GET estabelecimento BY ID", tags = "estabelecimentos")
    public ResponseEntity<EstabelecimentoResponseDto> getEstabelecimentoById(@PathVariable Long estabelecimentoId) {
        return ResponseEntity.ok(
            this.estabelecimentoMapper.toDto(this.estabelecimentoQuery.getEstabelecimentoById(estabelecimentoId))
        );
    }

    @PostMapping()
    @Operation(summary = "SAVE estabelecimento", tags = "estabelecimentos")
    public ResponseEntity<Void> saveEstabelecimento(@RequestBody EstabelecimentoSaveDto estabelecimento) {
        this.estabelecimentoQuery.saveEstabelecimento(estabelecimento);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{estabelecimentoId}")
    @Operation(summary = "UPDATE estabelecimento BY ID", tags = "estabelecimentos")
    public ResponseEntity<Void> updateEstabelecimentoById(
            @PathVariable Long estabelecimentoId,
            @RequestBody EstabelecimentoSaveDto estabelecimento
    ) {
        this.estabelecimentoQuery.updateEstabelecimentoById(estabelecimentoId, estabelecimento);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{estabelecimentoId}")
    @Operation(summary = "DELETE estabelecimento BY ID", tags = "estabelecimentos")
    public ResponseEntity<Void> deleteEstabelecimentoById(@PathVariable Long estabelecimentoId) {
       this.estabelecimentoQuery.deleteEstabelecimentoById(estabelecimentoId);
       return ResponseEntity.ok().build();
    }
}
