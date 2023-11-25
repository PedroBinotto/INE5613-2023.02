package br.ufsc.ine5613.query;


import br.ufsc.ine5613.dto.ProdutoSaveDto;
import br.ufsc.ine5613.model.Produto;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProdutoQuery {
    private final EntityManager em;

    public List<Produto> getProdutos(List<String> nomeFilter) {
        val query = this.em.createNamedQuery("getProdutos", Produto.class);
        query.setParameter("nomeFilter", nomeFilter);
        return query.getResultList();
    }

    public Produto getProdutoById(Long produtoId) {
        val query = this.em.createNamedQuery("getProdutoById", Produto.class);
        query.setParameter("produtoId", produtoId);
        return query.getSingleResult();
    }

    @Transactional
    public void saveProduto(ProdutoSaveDto produto) {
        val query = this.em.createNamedQuery("saveProduto", Produto.class);
        query.setParameter("nome", produto.nome());
        query.setParameter("valor", produto.valor());
        query.executeUpdate();
    }

    @Transactional
    public void deleteProdutoById(Long produtoId) {
        val query = this.em.createNamedQuery("deleteProdutoById", Produto.class);
        query.setParameter("produtoId", produtoId);
        query.executeUpdate();
    }
}
