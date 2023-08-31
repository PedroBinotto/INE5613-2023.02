package br.ufsc.ine5613.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.val;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufsc.ine5613.model.Cliente;
import br.ufsc.ine5613.query.ClienteQuery;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {
	private final ClienteQuery clienteQuery;

	@GetMapping()
	public ResponseEntity<List<Cliente>> getClientes(
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
			return ResponseEntity.ok(this.clienteQuery.getClientes(
					nomeFilter, sobrenomeFilter
			));
		} catch (NullPointerException e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
