package br.ufsc.ine5613.mapper;

import br.ufsc.ine5613.dto.EstabelecimentoResponseDto;
import br.ufsc.ine5613.dto.FuncionarioDetailCompositeDto;
import br.ufsc.ine5613.dto.LotacaoDetailCompositeDto;
import br.ufsc.ine5613.dto.LotacaoResponseDto;
import br.ufsc.ine5613.enums.CargoEnum;
import br.ufsc.ine5613.enums.UfEnum;
import br.ufsc.ine5613.model.Cargo;
import br.ufsc.ine5613.model.Estabelecimento;
import jdk.jshell.EvalException;
import lombok.val;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class LotacaoMapper implements DataMapper<LotacaoResponseDto, LotacaoDetailCompositeDto> {
    @Autowired private UfMapper ufMapper;

    @Mapping(target = "funcionario", ignore = true)
    @Mapping(target = "estabelecimento", ignore = true)
    @Mapping(target = "cargo", ignore = true)
    public abstract LotacaoResponseDto toDto(LotacaoDetailCompositeDto lotacao);

    @AfterMapping()
    void afterDtoMapping(LotacaoDetailCompositeDto lotacao, @MappingTarget LotacaoResponseDto dto) {
        dto.setFuncionario(new FuncionarioDetailCompositeDto(
                lotacao.funcionarioId(),
                lotacao.funcionarioCpf(),
                lotacao.funcionarioNome(),
                lotacao.funcionarioSobrenome()
        ));
        dto.setEstabelecimento(new EstabelecimentoResponseDto(
                lotacao.estabelecimentoId(),
                lotacao.estabelecimentoEndereco(),
                this.ufMapper.toDto(UfEnum.getById(lotacao.estabelecimentoUfId()))
        ));
        dto.setCargo(CargoEnum.valueOf(lotacao.funcionarioCargo()));
    }
}
