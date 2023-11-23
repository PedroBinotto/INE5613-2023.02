package br.ufsc.ine5613.dto;

import br.ufsc.ine5613.enums.CargoEnum;
import lombok.Data;

@Data
public class LotacaoResponseDto {
    private FuncionarioDetailCompositeDto funcionario;
    private EstabelecimentoResponseDto estabelecimento;
    private CargoEnum cargo;
}
