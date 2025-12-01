package br.ufsm.csi.Trabalho_POOW2.controller;

import br.ufsm.csi.Trabalho_POOW2.model.Funcionario.Funcionario;
import br.ufsm.csi.Trabalho_POOW2.service.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200") // <--- Importante para o Angular
@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    private final FuncionarioService service;

    public FuncionarioController(FuncionarioService service) {
        this.service = service;
    }

    // GET /funcionario (Padrão REST)
    @Operation(summary = "Listar funcionários", description = "Retorna lista de funcionários")
    @GetMapping
    public List<Funcionario> listar() {
        return this.service.listar();
    }

    // GET /funcionario/{id}
    @Operation(summary = "Buscar funcionário por Id", description = "Retorna um funcionário com o ID informado")
    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> getFuncionarioId(@PathVariable Long id) {
        Funcionario funcionario = this.service.getFuncionario(id);
        return funcionario != null ? ResponseEntity.ok(funcionario) : ResponseEntity.notFound().build();
    }

    // POST /funcionario
    @Operation(summary = "Criar um novo funcionário", description = "Cria um novo funcionário")
    @PostMapping
    @Transactional
    public ResponseEntity<Funcionario> salvar(@RequestBody @Valid Funcionario funcionario, UriComponentsBuilder uriBuilder) {
        this.service.salvar(funcionario);
        URI uri = uriBuilder.path("/funcionario/{uuid}").buildAndExpand(funcionario.getUuid()).toUri();
        return ResponseEntity.created(uri).body(funcionario);
    }

    // PUT /funcionario/{id} (Ajustado para receber ID na URL)
    @Operation(summary = "Atualizar funcionário por Id", description = "Atualiza os dados de um funcionário usando Id")
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Funcionario> atualizar(@PathVariable Long id, @RequestBody Funcionario funcionario) {
        funcionario.setId(id); // Garante a consistência
        this.service.atualizar(funcionario);
        return ResponseEntity.ok(funcionario);
    }

    // DELETE /funcionario/{id}
    @Operation(summary = "Excluir funcionário", description = "Remove um funcionário com o ID informado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        this.service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}