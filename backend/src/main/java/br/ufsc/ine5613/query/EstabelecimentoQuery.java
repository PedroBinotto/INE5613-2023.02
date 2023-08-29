package br.ufsc.ine5613.query;

import br.ufsc.ine5613.dto.EstabelecimentoSaveDto;
import br.ufsc.ine5613.model.Estabelecimento;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EstabelecimentoQuery {
    private final EntityManager em;

    public List<Estabelecimento> getEstabelecimentos(List<Long> ufFilter) {
        val query = this.em.createNamedQuery("getEstabelecimentos", Estabelecimento.class);
        query.setParameter("ufFilter", ufFilter);
        return query.getResultList();
    }

    public Estabelecimento getEstabelecimentoById(Long estabelecimentoId) {
        val query = this.em.createNamedQuery("getEstabelecimentoById", Estabelecimento.class);
        query.setParameter("estabelecimentoId", estabelecimentoId);
        return query.getSingleResult();
    }

    @Transactional
    public void saveEstabelecimento(EstabelecimentoSaveDto estabelecimento) {
        val query = this.em.createNamedQuery("saveEstabelecimento");
        query.setParameter("endereco", estabelecimento.endereco());
        query.setParameter("ufId", estabelecimento.ufId());
        query.executeUpdate();
    }

    @Transactional
    public void updateEstabelecimento(
            Long estabelecimentoId,
            EstabelecimentoSaveDto estabelecimento
    ) {
        val query = this.em.createNamedQuery("updateEstabelecimento");
        query.setParameter("estabelecimentoId", estabelecimentoId);
        query.setParameter("endereco", estabelecimento.endereco());
        query.executeUpdate();
    }

    @Transactional
    public void deleteEstabelecimentoById(Long estabelecimentoId) {
        val query = this.em.createNamedQuery("deleteEstabelecimentoById");
        query.setParameter("estabelecimentoId", estabelecimentoId);
        query.executeUpdate();
    }
}
