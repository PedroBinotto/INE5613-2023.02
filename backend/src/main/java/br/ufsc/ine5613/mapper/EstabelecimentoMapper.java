package br.ufsc.ine5613.mapper;

import br.ufsc.ine5613.dto.EstabelecimentoResponseDto;
import br.ufsc.ine5613.enums.UfEnum;
import br.ufsc.ine5613.model.Estabelecimento;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class EstabelecimentoMapper implements DataMapper<EstabelecimentoResponseDto, Estabelecimento> {
    @Autowired private UfMapper ufMapper;

    @Mapping( target = "uf", ignore = true )
    public abstract EstabelecimentoResponseDto toDto(Estabelecimento estabelecimento);

    @AfterMapping
    void afterDtoMapping(@MappingTarget EstabelecimentoResponseDto dto, Estabelecimento estabelecimento) {
        dto.setUf(this.ufMapper.toDto(UfEnum.getById(estabelecimento.getUfId())));
    }
}
