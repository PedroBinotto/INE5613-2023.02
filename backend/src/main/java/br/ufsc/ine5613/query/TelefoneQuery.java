package br.ufsc.ine5613.query;

import br.ufsc.ine5613.model.Telefone;
import jakarta.persistence.EntityManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class TelefoneQuery {
  private final EntityManager em;

  public List<Telefone> getTelefonesByPessoaFisicaId(Long pessoaFisicaId) {
    val query = this.em.createNamedQuery("getTelefonesByPessoaFisicaId", Telefone.class);
    query.setParameter("pessoaFisicaId", pessoaFisicaId);
    return query.getResultList();
  }

  @Transactional
  public void saveTelefone(Long pessoaFisicaId, String telefone) {
    val query = this.em.createNamedQuery("saveTelefone");
    query.setParameter("pessoaFisicaId", pessoaFisicaId);
    query.setParameter("telefone", telefone);
    query.executeUpdate();
  }

  @Transactional
  public void deleteTelefone(Long pessoaFisicaId, Long telefoneId) {
    val query = this.em.createNamedQuery("deleteTelefone");
    query.setParameter("pessoaFisicaId", pessoaFisicaId);
    query.setParameter("telefoneId", telefoneId);
    query.executeUpdate();
  }
}
