package br.ufsc.ine5613.query;

import br.ufsc.ine5613.dto.LotacaoDetailCompositeDto;
import br.ufsc.ine5613.dto.LotacaoSaveDto;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LotacaoQuery {
    private final EntityManager em;

    public List<LotacaoDetailCompositeDto> getLotacoes(
            List<Long> funcionarioFilter,
            List<Long> estabelecimentoFilter,
            List<String> cargoFilter
    ) {
        val query = this.em.createNamedQuery("getLotacoes", LotacaoDetailCompositeDto.class);
        query.setParameter("funcionarioFilter", funcionarioFilter);
        query.setParameter("estabelecimentoFilter", estabelecimentoFilter);
        query.setParameter("cargoFilter", cargoFilter);
        return query.getResultList();
    }

    public LotacaoDetailCompositeDto getLotacaoById(
            Long funcionarioId,
            Long estabelecimentoId,
            Long cargoId
    ) {
        val query = this.em.createNamedQuery("getLotacaoById", LotacaoDetailCompositeDto.class);
        query.setParameter("funcionarioId", funcionarioId);
        query.setParameter("estabelecimentoId", estabelecimentoId);
        query.setParameter("cargoId", cargoId);
        return query.getSingleResult();
    }

    @Transactional
    public void saveLotacao(LotacaoSaveDto lotacao) {
        val query = this.em.createNamedQuery("saveLotacao");
        query.setParameter("funcionarioId", lotacao.funcionarioId());
        query.setParameter("estabelecimentoId", lotacao.estabelecimentoId());
        query.setParameter("cargoId", lotacao.cargoId());
        query.executeUpdate();
    }

    @Transactional
    public void deleteLotacaoById(
            Long funcionarioId,
            Long estabelecimentoId,
            Long cargoId
    ) {
        val query = this.em.createNamedQuery("deleteLotacao");
        query.setParameter("funcionarioId", funcionarioId);
        query.setParameter("estabelecimentoId", estabelecimentoId);
        query.setParameter("cargoId", cargoId);
        query.executeUpdate();
    }
}
