package br.ufsc.ine5613.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UfEnum {

    AC(1L,"AC","Acre"),
    AL(2L,"AL","Alagoas"),
    AM(3L,"AM","Amazonas"),
    AP(4L,"AP","Amapá"),
    BA(5L,"BA","Bahia"),
    CE(6L,"CE","Ceará"),
    DF(7L,"DF","Distrito Federal"),
    ES(8L,"ES","Espírito Santo"),
    GO(9L,"GO","Goiás"),
    MA(10L,"MA","Maranhão"),
    MG(11L,"MG","Minas Gerais"),
    MS(12L,"MS","Mato Grosso do Sul"),
    MT(13L,"MT","Mato Grosso"),
    PA(14L,"PA","Pará"),
    PB(15L,"PB","Paraíba"),
    PE(16L,"PE","Pernambuco"),
    PI(17L,"PI","Piauí"),
    PR(18L,"PR","Paraná"),
    RJ(19L,"RJ","Rio de Janeiro"),
    RN(20L,"RN","Rio Grande do Norte"),
    RO(21L,"RO","Rondônia"),
    RR(22L,"RR","Roraima"),
    RS(23L,"RS","Rio Grande do Sul"),
    SC(24L,"SC","Santa Catarina"),
    SE(25L,"SE","Sergipe"),
    SP(26L,"SP","São Paulo"),
    TO(27L,"TO","Tocantins");

    public static UfEnum getBySigla(String sigla) {
        for (UfEnum uf : UfEnum.values()) {
            if (uf.toString().equals(sigla)) {
                return uf;
            }
        }
        return null;
    }

    public static UfEnum getById(Long id) {
        for (UfEnum uf : UfEnum.values()) {
            if (uf.getId().equals(id)) {
                return uf;
            }
        }
        return null;
    }

    private final Long id;
    private final String sigla;
    private final String nome;
}