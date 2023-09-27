package br.ufsc.ine5613.query;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.val;

import org.springframework.stereotype.Component;

import br.ufsc.ine5613.dto.ClienteDetailCompositeDto;
import br.ufsc.ine5613.dto.ClienteSaveDto;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class ClienteQuery {
	private final EntityManager em;

	public List<ClienteDetailCompositeDto> getClientes(
			List<String> nomeFilter,
			List<String> sobrenomeFilter,
			List<String> cpfFilter
	) {
		val query = this.em.createNamedQuery("getClientes", ClienteDetailCompositeDto.class);
		query.setParameter("nomeFilter", nomeFilter);
		query.setParameter("sobrenomeFilter", sobrenomeFilter);
		query.setParameter("cpfFilter", cpfFilter);
		return query.getResultList();
	}

	public ClienteDetailCompositeDto getClienteById(Long clienteId) {
		val query = this.em.createNamedQuery("getClienteByUd", ClienteDetailCompositeDto.class);
		query.setParameter("clienteId", clienteId);
		return query.getSingleResult();
	}

	@Transactional
	public void saveCliente(ClienteSaveDto cliente) {
		val query = this.em.createNamedQuery("saveCliente");
		query.setParameter("pessoaFisicaId", cliente.pessoaFisicaId());
		query.executeUpdate();
	}

	@Transactional
	public void deleteClienteById(Long clienteId) {
		val query = this.em.createNamedQuery("deleteCliente");
		query.setParameter("clienteId", clienteId);
		query.executeUpdate();
	}
}
