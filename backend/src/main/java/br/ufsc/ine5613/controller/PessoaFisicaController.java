package br.ufsc.ine5613.controller;

import br.ufsc.ine5613.dto.PessoaFisicaDetailCompositeDto;
import br.ufsc.ine5613.dto.PessoaFisicaResponseDto;
import br.ufsc.ine5613.dto.PessoaFisicaSaveDto;
import br.ufsc.ine5613.mapper.PessoaFisicaMapper;
import br.ufsc.ine5613.query.PessoaFisicaQuery;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pessoasfisicas")
@RequiredArgsConstructor
public class PessoaFisicaController {
    private final PessoaFisicaQuery pessoaFisicaQuery;
    private final PessoaFisicaMapper pessoaFisicaMapper;

    @GetMapping()
    public ResponseEntity<List<PessoaFisicaResponseDto>> getPessoasFisicas(
            @RequestParam(required = false) Optional<String[]> nome,
            @RequestParam(required = false) Optional<String[]> sobrenome,
            @RequestParam(required = false) Optional<String[]> cpf
    ) {
        try {
            val nomeFilter = nome
                    .map(value -> Arrays.stream(value).map(String::toUpperCase).collect(Collectors.toList()))
                    .orElseGet(ArrayList::new);
            val sobrenomeFilter = sobrenome
                    .map(strings -> Arrays.stream(strings).map(String::toUpperCase).collect(Collectors.toList()))
                    .orElseGet(ArrayList::new);
            val cpfFilter = cpf.map(Arrays::asList).orElseGet(() -> new ArrayList<>() {});
            return ResponseEntity.ok(
                this.pessoaFisicaMapper.toDto(this.pessoaFisicaQuery.getPessoasFisicas(nomeFilter, sobrenomeFilter, cpfFilter))
            );
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{pessoaFisicaId}")
    public ResponseEntity<PessoaFisicaDetailCompositeDto> getPessoaFisicaById(@PathVariable Long pessoaFisicaId) {
        return ResponseEntity.ok(this.pessoaFisicaQuery.getPessoaFisicaById(pessoaFisicaId));
    }

    @PostMapping()
    public ResponseEntity<Void> savePessoaFisica(@RequestBody PessoaFisicaSaveDto pessoaFisica) {
        this.pessoaFisicaQuery.savePessoaFisica(pessoaFisica);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{pessoaFisicaId}")
    public ResponseEntity<Void> updatePessoaFisicaById(
            @PathVariable Long pessoaFisicaId,
            @RequestBody PessoaFisicaSaveDto pessoaFisica
    ) {
        this.pessoaFisicaQuery.updatePessoaFisicaById(pessoaFisicaId, pessoaFisica);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{pessoaFisicaId}")
    public ResponseEntity<Void> deletePessoaFisicaById(@PathVariable Long pessoaFisicaId) {
        this.pessoaFisicaQuery.deletePessoaFisicaById(pessoaFisicaId);
        return ResponseEntity.ok().build();
    }
}
