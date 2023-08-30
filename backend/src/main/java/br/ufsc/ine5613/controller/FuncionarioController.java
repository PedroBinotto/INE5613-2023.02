package br.ufsc.ine5613.controller;

import br.ufsc.ine5613.dto.FuncionarioDetailCompositeDto;
import br.ufsc.ine5613.dto.FuncionarioSaveDto;
import br.ufsc.ine5613.query.FuncionarioQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
@RequiredArgsConstructor
public class FuncionarioController {
    private final FuncionarioQuery funcionarioQuery;

    @GetMapping()
    public ResponseEntity<List<FuncionarioDetailCompositeDto>> getFuncionarios() {
        return ResponseEntity.ok(this.funcionarioQuery.getFuncionarios());
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
    public ResponseEntity<Void> saveFuncionario(@PathVariable Long funcionarioId) {
        this.funcionarioQuery.deleteFuncionarioById(funcionarioId);
        return ResponseEntity.ok().build();
    }
}
