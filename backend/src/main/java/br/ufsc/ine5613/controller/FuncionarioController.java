package br.ufsc.ine5613.controller;

import br.ufsc.ine5613.dto.FuncionarioDetailCompositeDto;
import br.ufsc.ine5613.dto.FuncionarioSaveDto;
import br.ufsc.ine5613.query.FuncionarioQuery;
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
@RequestMapping("/api/funcionarios")
@RequiredArgsConstructor
public class FuncionarioController {
    private final FuncionarioQuery funcionarioQuery;

    @GetMapping()
    public ResponseEntity<List<FuncionarioDetailCompositeDto>> getFuncionarios(
            @RequestParam(required = false) Optional<String[]> nome,
            @RequestParam(required = false) Optional<String[]> sobrenome
    ) {
        try {

            val nomeFilter = nome
                    .map(value -> Arrays.stream(value).map(String::toUpperCase).collect(Collectors.toList()))
                    .orElseGet(ArrayList::new);
            val sobrenomeFilter = sobrenome
                    .map(strings -> Arrays.stream(strings).map(String::toUpperCase).collect(Collectors.toList()))
                    .orElseGet(ArrayList::new);
            return ResponseEntity.ok(this.funcionarioQuery.getFuncionarios(
                   nomeFilter, sobrenomeFilter
            ));
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{funcionarioId}")
    public ResponseEntity<FuncionarioDetailCompositeDto> getFuncionarioById(@PathVariable Long funcionarioId) {
        return ResponseEntity.ok(this.funcionarioQuery.getFuncionarioById(funcionarioId));
    }

    @PostMapping()
    public ResponseEntity<Void> saveFuncionario(@RequestBody FuncionarioSaveDto funcionario) {
        this.funcionarioQuery.saveFuncionario(funcionario);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{funcionarioId}")
    public ResponseEntity<Void> deleteFuncionarioById(@PathVariable Long funcionarioId) {
        this.funcionarioQuery.deleteFuncionarioById(funcionarioId);
        return ResponseEntity.ok().build();
    }
}
