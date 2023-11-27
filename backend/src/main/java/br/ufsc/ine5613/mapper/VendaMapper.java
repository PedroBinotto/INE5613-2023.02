package br.ufsc.ine5613.mapper;

import br.ufsc.ine5613.dto.*;
import br.ufsc.ine5613.enums.CargoEnum;
import br.ufsc.ine5613.enums.UfEnum;
import java.util.List;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class VendaMapper {

  @Autowired private UfMapper ufMapper;

  @Mapping(target = "lotacao", ignore = true)
  @Mapping(target = "cliente", ignore = true)
  @Mapping(target = "produtos", ignore = true) // TODO: ver se nao da pra usar um mapper aninhado
  public abstract VendaResponseDto toResponseDto(VendaDetailCompositeDto composite);

  @AfterMapping
  void afterResponseDtoMapping(
      VendaDetailCompositeDto composite, @MappingTarget VendaResponseDto response) {
    response.setLotacao(
        new LotacaoResponseDto(
            new FuncionarioDetailCompositeDto(
                composite.getFuncionarioId(),
                composite.getFuncionarioCpf(),
                composite.getFuncionarioNome(),
                composite.getClienteSobrenome()),
            new EstabelecimentoResponseDto(
                composite.getEstabelecimentoId(),
                composite.getEstabelecimentoEndereco(),
                ufMapper.toDto(UfEnum.getById(composite.getEstabelecimentoUfId()))),
            CargoEnum.valueOf(composite.getFuncionarioCargo())));
    response.setCliente(
        new ClienteDetailCompositeDto(
            composite.getClienteId(),
            composite.getClienteCpf(),
            composite.getClienteNome(),
            composite.getClienteSobrenome()));
    response.setProdutos(
        composite.getProdutos().stream()
            .map(
                it ->
                    new VendaResponseDto.VendaProdutoResponseDto(
                        new ProdutoResponseDto(it.id(), it.nome(), it.valor()), it.quantidade()))
            .toList());
  }

  public abstract List<VendaResponseDto> toResponseDto(List<VendaDetailCompositeDto> composite);
}
