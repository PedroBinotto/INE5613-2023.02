package br.ufsc.ine5613.query;

import br.ufsc.ine5613.dto.VendaDetailCompositeDto;
import br.ufsc.ine5613.dto.VendaIdWrapperDto;
import br.ufsc.ine5613.dto.VendaSaveDto;
import br.ufsc.ine5613.model.Venda;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.sql.Statement;
import java.util.List;

@Component
@RequiredArgsConstructor
public class VendaQuery {
    private final EntityManager em;

    public List<VendaDetailCompositeDto> getVendas(
        List<String> funcionarioFilter,
        List<String> clienteFilter,
        List<Long> estabelecimentoFilter
    ) {
        val query = this.em.createNamedQuery("getVendas", VendaDetailCompositeDto.class);
        query.setParameter("estabelecimentoFilter", estabelecimentoFilter);
        query.setParameter("clienteFilter", clienteFilter);
        query.setParameter("funcionarioFilter", funcionarioFilter);
        return query.getResultList();
    }

    @Transactional
    public Long saveVenda(VendaSaveDto venda) {
        val query = this.em.createNamedQuery("saveVenda");
        query.setParameter("clienteId", venda.clienteId());
        query.setParameter("funcionarioId", venda.funcionarioId());
        query.setParameter("estabelecimentoId", venda.estabelecimentoId());
        query.setParameter("cargoId", venda.cargo().getId());
        return (Long) query.getSingleResult();
    }

    @Transactional
    public void saveVendaProdutos(VendaSaveDto.VendaProdutoSaveDto produto, Long vendaId) {
        val query = this.em.createNamedQuery("saveVendaProdutos");
        query.setParameter("vendaId", vendaId);
        query.setParameter("produtoId", produto.produtoId());
        query.setParameter("quantidade", produto.quantidade());
        query.executeUpdate();
    }
}
