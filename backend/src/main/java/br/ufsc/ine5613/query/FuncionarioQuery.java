package br.ufsc.ine5613.query;

import br.ufsc.ine5613.dto.FuncionarioDetailCompositeDto;
import br.ufsc.ine5613.dto.FuncionarioSaveDto;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FuncionarioQuery {
    private final EntityManager em;

    public List<FuncionarioDetailCompositeDto> getFuncionarios() {
        val query = this.em.createNamedQuery("getFuncionarios", FuncionarioDetailCompositeDto.class);
        return query.getResultList();
    }

    public FuncionarioDetailCompositeDto getFuncionarioById(Long funcionarioId) {
        val query = this.em.createNamedQuery("getFuncionarioById", FuncionarioDetailCompositeDto.class);
        query.setParameter("funcionarioId", funcionarioId);
        return query.getSingleResult();
    }

    @Transactional
    public void saveFuncionario(FuncionarioSaveDto funcionario) {
        val query = this.em.createNamedQuery("saveFuncionario");
        query.setParameter("pessoaFisicaId", funcionario.pessoaFisicaId());
        query.executeUpdate();
    }

    @Transactional
    public void deleteFuncionarioById(Long funcionarioId) {
        val query = this.em.createNamedQuery("deleteFuncionario");
        query.setParameter("funcionarioId", funcionarioId);
        query.executeUpdate();
    }
}
