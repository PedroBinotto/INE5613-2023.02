package br.ufsc.ine5613.query;

import br.ufsc.ine5613.dto.PessoaFisicaDetailCompositeDto;
import br.ufsc.ine5613.dto.PessoaFisicaSaveDto;
import br.ufsc.ine5613.model.PessoaFisica;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PessoaFisicaQuery {
  private final EntityManager em;

  public List<PessoaFisica> getPessoasFisicas(
      List<String> nomeFilter, List<String> sobrenomeFilter, List<String> cpfFilter) {
    val query = this.em.createNamedQuery("getPessoasFisicas", PessoaFisica.class);
    query.setParameter("nomeFilter", nomeFilter);
    query.setParameter("sobrenomeFilter", sobrenomeFilter);
    query.setParameter("cpfFilter", cpfFilter);
    return query.getResultList();
  }

  public PessoaFisicaDetailCompositeDto getPessoaFisicaById(Long pessoaFisicaId) {
    val query =
        this.em.createNamedQuery("getPessoaFisicaById", PessoaFisicaDetailCompositeDto.class);
    query.setParameter("pessoaFisicaId", pessoaFisicaId);
    return query.getSingleResult();
  }

  @Transactional
  public void savePessoaFisica(PessoaFisicaSaveDto pessoaFisica) {
    val query = this.em.createNamedQuery("savePessoaFisica");
    query.setParameter("nome", pessoaFisica.nome());
    query.setParameter("sobrenome", pessoaFisica.sobrenome());
    query.setParameter("cpf", pessoaFisica.cpf());
    query.executeUpdate();
  }

  @Transactional
  public void updatePessoaFisicaById(Long pessoaFisicaId, PessoaFisicaSaveDto pessoaFisica) {
    val query = this.em.createNamedQuery("updatePessoaFisica");
    query.setParameter("pessoaFisicaId", pessoaFisicaId);
    query.setParameter("nome", pessoaFisica.nome());
    query.setParameter("sobrenome", pessoaFisica.sobrenome());
    query.executeUpdate();
  }

  @Transactional
  public void deletePessoaFisicaById(Long pessoaFisicaId) {
    val query = this.em.createNamedQuery("deletePessoaFisicaById");
    query.setParameter("pessoaFisicaId", pessoaFisicaId);
    query.executeUpdate();
  }
}
