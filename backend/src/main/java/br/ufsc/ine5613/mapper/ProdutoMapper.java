package br.ufsc.ine5613.mapper;

import br.ufsc.ine5613.dto.ProdutoResponseDto;
import br.ufsc.ine5613.model.Produto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoMapper extends DataMapper<ProdutoResponseDto, Produto> {}
