package br.ufsc.ine5613.controller;

import br.ufsc.ine5613.enums.UfEnum;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.ufsc.ine5613.dto.ExemploDto;

@RestController
@RequestMapping("/api/exemplo")
@RequiredArgsConstructor
public class ExemploController {
	@GetMapping("/teste")
	public ResponseEntity<ExemploDto> getTeste() {
		return ResponseEntity.ok(new ExemploDto("Hello, World!"));
	}

	@GetMapping("/uf")
	public ResponseEntity<UfEnum> getTesteUf() {
		return ResponseEntity.ok(UfEnum.SC);
	}
}
