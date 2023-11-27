package br.ufsc.ine5613.mapper;

import java.util.List;

public interface DataMapper<DTO, ENTITY> {
  ENTITY toEntity(DTO dto);

  DTO toDto(ENTITY entity);

  List<ENTITY> toEntity(List<DTO> dtoList);

  List<DTO> toDto(List<ENTITY> entityList);
}
