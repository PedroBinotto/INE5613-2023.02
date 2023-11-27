package br.ufsc.ine5613.controller;

import br.ufsc.ine5613.dto.ClienteDetailCompositeDto;
import br.ufsc.ine5613.dto.ClienteSaveDto;
import br.ufsc.ine5613.query.ClienteQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
@Tag(name = "clientes")
public class ClienteController {
  private final ClienteQuery clienteQuery;

  @GetMapping()
  @Operation(summary = "GET clientes", tags = "clientes")
  public ResponseEntity<List<ClienteDetailCompositeDto>> getClientes(
      @RequestParam(required = false) Optional<String[]> nome,
      @RequestParam(required = false) Optional<String[]> sobrenome,
      @RequestParam(required = false) Optional<String[]> cpf) {
    try {
      val nomeFilter =
          nome // TODO: Mover lógica para Service e tentar eliminar repeticao em
               // Cliente/Funcionario/PessoaFisisca
              .map(
                  value ->
                      Arrays.stream(value).map(String::toUpperCase).collect(Collectors.toList()))
              .orElseGet(ArrayList::new);
      val sobrenomeFilter =
          sobrenome
              .map(
                  strings ->
                      Arrays.stream(strings).map(String::toUpperCase).collect(Collectors.toList()))
              .orElseGet(ArrayList::new);
      val cpfFilter = cpf.map(Arrays::asList).orElseGet(() -> new ArrayList<>() {});
      return ResponseEntity.ok(
          this.clienteQuery.getClientes(nomeFilter, sobrenomeFilter, cpfFilter));
    } catch (NullPointerException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @GetMapping("/{clienteId}")
  @Operation(summary = "GET cliente BY ID", tags = "clientes")
  public ResponseEntity<ClienteDetailCompositeDto> getClienteById(@PathVariable Long clienteId) {
    return ResponseEntity.ok(this.clienteQuery.getClienteById(clienteId));
  }

  @PostMapping()
  @Operation(summary = "SAVE cliente", tags = "clientes")
  public ResponseEntity<Void> saveCliente(@RequestBody ClienteSaveDto cliente) {
    this.clienteQuery.saveCliente(cliente);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{clienteId}")
  @Operation(summary = "DELETE cliente BY ID", tags = "clientes")
  public ResponseEntity<Void> deleteClienteById(@PathVariable Long clienteId) {
    this.clienteQuery.deleteClienteById(clienteId);
    return ResponseEntity.ok().build();
  }
}
