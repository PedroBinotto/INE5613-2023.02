package br.ufsc.ine5613.controller;

import br.ufsc.ine5613.dto.PessoaFisicaDetailCompositeDto;
import br.ufsc.ine5613.dto.PessoaFisicaResponseDto;
import br.ufsc.ine5613.dto.PessoaFisicaSaveDto;
import br.ufsc.ine5613.dto.TelefoneResponseDto;
import br.ufsc.ine5613.dto.TelefoneSaveDto;
import br.ufsc.ine5613.mapper.PessoaFisicaMapper;
import br.ufsc.ine5613.mapper.TelefoneMapper;
import br.ufsc.ine5613.query.PessoaFisicaQuery;
import br.ufsc.ine5613.query.TelefoneQuery;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/pessoasfisicas")
@RequiredArgsConstructor
@Tag(name = "pessoasfisicas")
public class PessoaFisicaController {
    private final PessoaFisicaQuery pessoaFisicaQuery;
    private final PessoaFisicaMapper pessoaFisicaMapper;
    private final TelefoneQuery telefoneQuery;
    private final TelefoneMapper telefoneMapper;

    @GetMapping()
    @ApiOperation(value = "GET pessoasfisicas", tags = "pessoasfisicas")
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
    @ApiOperation(value = "GET pessoafisica BY ID", tags = "pessoasfisicas")
    public ResponseEntity<PessoaFisicaDetailCompositeDto> getPessoaFisicaById(@PathVariable Long pessoaFisicaId) {
        return ResponseEntity.ok(this.pessoaFisicaQuery.getPessoaFisicaById(pessoaFisicaId));
    }

    @GetMapping("/{pessoaFisicaId}/telefones")
    @ApiOperation(value = "GET telefones BY pessoafisica ID", tags = "pessoasfisicas")
    public ResponseEntity<List<TelefoneResponseDto>> getTelefonesPessoaFisicaById(@PathVariable Long pessoaFisicaId) {
        return ResponseEntity.ok(this.telefoneMapper.toDto(this.telefoneQuery.getTelefonesByPessoaFisicaId(pessoaFisicaId)));
    }

    @PostMapping("/{pessoaFisicaId}/telefones")
    @ApiOperation(value = "SAVE telefone BY pessoafisica ID", tags = "pessoasfisicas")
    public ResponseEntity<Void> saveTelefonePessoaFisicaById(
            @PathVariable Long pessoaFisicaId,
            @RequestBody TelefoneSaveDto telefoneSaveDto
    ) {
        this.telefoneQuery.saveTelefone(pessoaFisicaId, telefoneSaveDto.telefone());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{pessoaFisicaId}/telefones/{telefoneId}")
    @ApiOperation(value = "DELETE telefone BY pessoafisica ID, telefone ID", tags = "pessoasfisicas")
    public ResponseEntity<PessoaFisicaDetailCompositeDto> deleteTelefonePessoaFisicaById(
            @PathVariable Long pessoaFisicaId,
            @PathVariable Long telefoneId
    ) {
        this.telefoneQuery.deleteTelefone(pessoaFisicaId, telefoneId);
        return ResponseEntity.ok().build();
    }

    @PostMapping()
    @ApiOperation(value = "SAVE pessoafisica", tags = "pessoasfisicas")
    public ResponseEntity<Void> savePessoaFisica(@RequestBody PessoaFisicaSaveDto pessoaFisica) {
        this.pessoaFisicaQuery.savePessoaFisica(pessoaFisica);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{pessoaFisicaId}")
    @ApiOperation(value = "UPDATE pessoafisica BY ID", tags = "pessoasfisicas")
    public ResponseEntity<Void> updatePessoaFisicaById(
            @PathVariable Long pessoaFisicaId,
            @RequestBody PessoaFisicaSaveDto pessoaFisica
    ) {
        this.pessoaFisicaQuery.updatePessoaFisicaById(pessoaFisicaId, pessoaFisica);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{pessoaFisicaId}")
    @ApiOperation(value = "DELETE pessoafisica BY ID", tags = "pessoasfisicas")
    public ResponseEntity<Void> deletePessoaFisicaById(@PathVariable Long pessoaFisicaId) {
        this.pessoaFisicaQuery.deletePessoaFisicaById(pessoaFisicaId);
        return ResponseEntity.ok().build();
    }
}
