package br.ufsc.ine5613.controller;

import br.ufsc.ine5613.model.Estabelecimento;
import br.ufsc.ine5613.query.EstabelecimentoQuery;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/estabelecimentos")
@RequiredArgsConstructor
public class EstabelecimentoController {
    private final EstabelecimentoQuery estabelecimentoQuery;

    @GetMapping()       // TODO: Filtros com @RequestParam
    public ResponseEntity<List<Estabelecimento>> getEstabelecimentos() {
        val resultSet = this.estabelecimentoQuery.getEstabelecimentos();
        System.out.println(resultSet);
        System.out.println(resultSet.get(0).getEndereco());
        return ResponseEntity.ok(resultSet);
    }
}
