package br.ufsc.ine5613.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.val;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PessoaFisicaDetailCompositeDto {
    private Long id;
    private String cpf;
    private String nome;
    private String sobrenome;
    private List<String> telefones;

    public PessoaFisicaDetailCompositeDto(
            Long id,
            String cpf,
            String nome,
            String sobrenome,
            String telefones
    ) throws JsonProcessingException {
        val om = new ObjectMapper();
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.sobrenome = sobrenome;
        val parsedTelefones = om.readValue(telefones, List.class);      // FIXME: Gambiarra
        this.telefones = parsedTelefones.get(0) == null
                ? new ArrayList<String>()
                : parsedTelefones;
    }
}
