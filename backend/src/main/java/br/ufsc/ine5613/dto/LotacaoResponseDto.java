package br.ufsc.ine5613.dto;

import br.ufsc.ine5613.enums.CargoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class LotacaoResponseDto {
    private FuncionarioDetailCompositeDto funcionario;
    private EstabelecimentoResponseDto estabelecimento;
    private CargoEnum cargo;
}
