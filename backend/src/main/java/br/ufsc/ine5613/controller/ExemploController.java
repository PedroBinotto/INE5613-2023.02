package br.ufsc.ine5613.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufsc.ine5613.dto.ExemploDto;

@RestController
@RequestMapping("/api/exemplo")
@RequiredArgsConstructor
public class ExemploController {

	@GetMapping("/teste")
	public ResponseEntity<ExemploDto> getTeste() {
		return ResponseEntity.ok(new ExemploDto("Hello, World!"));
	}
}
