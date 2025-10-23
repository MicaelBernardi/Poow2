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

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    private final FuncionarioService service;

    public FuncionarioController(FuncionarioService service) {
        this.service = service;
    }


    // GET - listar todos
    @Operation(summary = "Listar funcionários", description = "Retorna lista de funcionários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Funcionario.class)))
    })
    @GetMapping("/listar")
    public List<Funcionario> listar() {
        return this.service.listar();
    }


    // GET - buscar por ID
    @Operation(summary = "Buscar funcionário por Id", description = "Retorna um funcionário com o ID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionário encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Funcionario.class))),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> getFuncionarioId(@PathVariable Long id) {
        Funcionario funcionario = this.service.getFuncionario(id);
        return funcionario != null ? ResponseEntity.ok(funcionario) : ResponseEntity.notFound().build();
    }


    // GET - buscar por UUID
    @Operation(summary = "Buscar funcionário por Uuid", description = "Retorna um funcionário com o UUID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionário encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Funcionario.class))),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado")
    })
    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<Funcionario> getFuncionarioUuid(@PathVariable String uuid) {
        Funcionario funcionario = this.service.getFuncionarioUuid(uuid);
        return funcionario != null ? ResponseEntity.ok(funcionario) : ResponseEntity.notFound().build();
    }


    // POST - criar funcionário
    @Operation(summary = "Criar um novo funcionário", description = "Cria um novo funcionário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Funcionário criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Funcionario.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @PostMapping
    @Transactional
    public ResponseEntity<Funcionario> salvar(@RequestBody @Valid Funcionario funcionario, UriComponentsBuilder uriBuilder) {
        this.service.salvar(funcionario);
        URI uri = uriBuilder.path("/funcionario/{uuid}").buildAndExpand(funcionario.getUuid()).toUri();
        return ResponseEntity.created(uri).body(funcionario);
    }


    // PUT - atualizar funcionário
    @Operation(summary = "Atualizar funcionário por Id", description = "Atualiza os dados de um funcionário usando Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionário atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Funcionario.class))),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado")
    })
    @PutMapping
    @Transactional
    public ResponseEntity<Funcionario> atualizar(@RequestBody Funcionario funcionario) {
        this.service.atualizar(funcionario);
        return ResponseEntity.ok(funcionario);
    }


    // DELETE - remover funcionário
    @Operation(summary = "Excluir funcionário", description = "Remove um funcionário com o ID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Funcionário removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        this.service.excluir(id);
        return ResponseEntity.noContent().build();
    }


    // POST - atualizar por UUID
    @Operation(summary = "Atualizar funcionário por Uuid", description = "Atualiza um funcionário usando o UUID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado")
    })
    @PostMapping("/uuid")
    public ResponseEntity<Void> atualizarPorUUID(@RequestBody Funcionario funcionario) {
        this.service.atualizarUUID(funcionario);
        return ResponseEntity.ok().build();
    }
}
