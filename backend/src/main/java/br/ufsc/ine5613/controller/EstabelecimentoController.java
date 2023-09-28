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
import java.util.stream.Collectors;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/estabelecimentos")
@RequiredArgsConstructor
@Tag(name = "estabelecimentos")
public class EstabelecimentoController {
    private final EstabelecimentoQuery estabelecimentoQuery;
    private final EstabelecimentoMapper estabelecimentoMapper;

    @GetMapping()
    @ApiOperation(value = "GET estabelecimentos", tags = "estabelecimentos")
    public ResponseEntity<List<EstabelecimentoResponseDto>> getEstabelecimentos(
            @RequestParam(required = false) Optional<String[]> uf
    ) {
        try {
            Long[] ufFilter = uf.isPresent()
                    ? Arrays
                        .stream(uf.get())
                        .map(el -> UfEnum.getBySigla(el.toUpperCase()).getId() )
                        .collect(Collectors.toList()).toArray(new Long[] {})
                    : new Long[] {};
            return ResponseEntity.ok(
                this.estabelecimentoMapper
                    .toDto(this.estabelecimentoQuery.getEstabelecimentos(Arrays.stream(ufFilter).toList()))
            );
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{estabelecimentoId}")
    @ApiOperation(value = "GET estabelecimento BY ID", tags = "estabelecimentos")
    public ResponseEntity<EstabelecimentoResponseDto> getEstabelecimentoById(@PathVariable Long estabelecimentoId) {
        return ResponseEntity.ok(
            this.estabelecimentoMapper.toDto(this.estabelecimentoQuery.getEstabelecimentoById(estabelecimentoId))
        );
    }

    @PostMapping()
    @ApiOperation(value = "SAVE estabelecimento", tags = "estabelecimentos")
    public ResponseEntity<Void> saveEstabelecimento(@RequestBody EstabelecimentoSaveDto estabelecimento) {
        this.estabelecimentoQuery.saveEstabelecimento(estabelecimento);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{estabelecimentoId}")
    @ApiOperation(value = "UPDATE estabelecimento BY ID", tags = "estabelecimentos")
    public ResponseEntity<Void> updateEstabelecimentoById(
            @PathVariable Long estabelecimentoId,
            @RequestBody EstabelecimentoSaveDto estabelecimento
    ) {
        this.estabelecimentoQuery.updateEstabelecimentoById(estabelecimentoId, estabelecimento);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{estabelecimentoId}")
    @ApiOperation(value = "DELETE estabelecimento BY ID", tags = "estabelecimentos")
    public ResponseEntity<Void> deleteEstabelecimentoById(@PathVariable Long estabelecimentoId) {
       this.estabelecimentoQuery.deleteEstabelecimentoById(estabelecimentoId);
       return ResponseEntity.ok().build();
    }
}
