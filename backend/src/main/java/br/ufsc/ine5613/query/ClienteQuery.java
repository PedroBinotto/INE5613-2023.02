package br.ufsc.ine5613.query;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import br.ufsc.ine5613.model.Cliente;

import jakarta.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class ClienteQuery {
	private final EntityManager em;

	public List<Cliente> getClientes(List<String> nomeFilter, List<String> sobrenomeFilter) {
		return new ArrayList<Cliente>() {};
	}
}
