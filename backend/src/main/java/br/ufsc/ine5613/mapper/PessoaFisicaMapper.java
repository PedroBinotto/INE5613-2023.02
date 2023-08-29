package br.ufsc.ine5613.mapper;

import br.ufsc.ine5613.dto.PessoaFisicaResponseDto;
import br.ufsc.ine5613.model.PessoaFisica;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PessoaFisicaMapper extends DataMapper<PessoaFisicaResponseDto, PessoaFisica> {}
