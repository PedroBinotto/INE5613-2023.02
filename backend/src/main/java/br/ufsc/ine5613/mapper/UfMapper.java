package br.ufsc.ine5613.mapper;

import br.ufsc.ine5613.dto.UfDto;
import br.ufsc.ine5613.enums.UfEnum;
import br.ufsc.ine5613.model.Uf;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UfMapper extends DataMapper<UfDto, Uf> {
    UfDto toDto(UfEnum ufEnum);
}
