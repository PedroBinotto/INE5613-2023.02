package br.ufsc.ine5613.query;

import br.ufsc.ine5613.dto.VendaDetailCompositeDto;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class VendaQuery {
    private final EntityManager em;

    public List<VendaDetailCompositeDto> getVendas() {
        val query = this.em.createNamedQuery("getVendas", VendaDetailCompositeDto.class);
        return query.getResultList();
    }
}
