package br.ufsc.ine5613.mapper;

import org.mapstruct.Mapper;

import br.ufsc.ine5613.dto.TelefoneResponseDto;
import br.ufsc.ine5613.model.Telefone;

@Mapper(componentModel = "spring")
public interface TelefoneMapper extends DataMapper<TelefoneResponseDto, Telefone> {}
