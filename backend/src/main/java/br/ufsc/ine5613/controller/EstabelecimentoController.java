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

@RestController
@RequestMapping("/api/estabelecimentos")
@RequiredArgsConstructor
public class EstabelecimentoController {
    private final EstabelecimentoQuery estabelecimentoQuery;
    private final EstabelecimentoMapper estabelecimentoMapper;

    @GetMapping()
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
                    .toDto(
                        this.estabelecimentoQuery
                            .getEstabelecimentos(
                                Arrays.stream(ufFilter).toList()
                            )
                        )
            );
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{estabelecimentoId}")
    public ResponseEntity<EstabelecimentoResponseDto> getEstabelecimentoById(@PathVariable Long estabelecimentoId) {
        return ResponseEntity.ok(
            this.estabelecimentoMapper.toDto(this.estabelecimentoQuery.getEstabelecimentoById(estabelecimentoId))
        );
    }

    @PostMapping()
    public ResponseEntity<Void> saveEstabelecimento(@RequestBody EstabelecimentoSaveDto estabelecimento) {
        this.estabelecimentoQuery.saveEstabelecimento(estabelecimento);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{estabelecimentoId}")
    public ResponseEntity<Void> updateEstabelecimentoById(
            @PathVariable Long estabelecimentoId,
            @RequestBody EstabelecimentoSaveDto estabelecimento
    ) {
        this.estabelecimentoQuery.updateEstabelecimento(estabelecimentoId, estabelecimento);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{estabelecimentoId}")
    public ResponseEntity<Void> deleteEstabelecimentoById(@PathVariable Long estabelecimentoId) {
       this.estabelecimentoQuery.deleteEstabelecimentoById(estabelecimentoId);
       return ResponseEntity.ok().build();
    }
}
