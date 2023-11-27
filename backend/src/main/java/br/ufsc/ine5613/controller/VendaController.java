package br.ufsc.ine5613.controller;

import br.ufsc.ine5613.dto.VendaDetailCompositeDto;
import br.ufsc.ine5613.enums.UfEnum;
import br.ufsc.ine5613.query.VendaQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vendas")
@RequiredArgsConstructor
@Tag(name = "vendas")
public class VendaController {
    private final VendaQuery vendaQuery;

    @GetMapping()
    @Operation(summary = "GET vendas", tags = "vendas")
    public ResponseEntity<List<VendaDetailCompositeDto>> getVendas(
            @RequestParam(required = false) Optional<UfEnum[]> uf
    ) {
        try {
            val res = this.vendaQuery.getVendas();
            System.out.println(res);
            return ResponseEntity.ok(res);
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
