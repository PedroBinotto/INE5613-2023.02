package br.ufsc.ine5613.mapper;

import br.ufsc.ine5613.dto.TelefoneResponseDto;
import br.ufsc.ine5613.model.Telefone;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TelefoneMapper extends DataMapper<TelefoneResponseDto, Telefone> {}
