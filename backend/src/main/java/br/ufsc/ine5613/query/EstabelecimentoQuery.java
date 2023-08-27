package br.ufsc.ine5613.query;

import br.ufsc.ine5613.model.Estabelecimento;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EstabelecimentoQuery {
    private final EntityManager em;

    public List<Estabelecimento> getEstabelecimentos() {
        return this.em.createNamedQuery("getEstabelecimentos", Estabelecimento.class).getResultList();
    }
}
