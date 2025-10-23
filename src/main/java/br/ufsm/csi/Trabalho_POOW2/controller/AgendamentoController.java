package br.ufsm.csi.Trabalho_POOW2.controller;

import br.ufsm.csi.Trabalho_POOW2.model.Agendamento.Agendamento;
import br.ufsm.csi.Trabalho_POOW2.service.AgendamentoService;
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
@RequestMapping("/agendamento")
public class AgendamentoController {

    private final AgendamentoService service;

    public AgendamentoController(AgendamentoService service) {
        this.service = service;
    }


    // GET - listar todos
    @Operation(summary = "Listar agendamentos", description = "Retorna a lista de agendamentos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Agendamento.class)))
    })
    @GetMapping("/listar")
    public List<Agendamento> listar() {
        return this.service.listar();
    }


    // GET - buscar por ID
    @Operation(summary = "Buscar agendamento por Id", description = "Retorna um agendamento com o ID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamento encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Agendamento.class))),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> getAgendamentoId(@PathVariable Long id) {
        Agendamento agendamento = this.service.getAgendamento(id);
        return agendamento != null ? ResponseEntity.ok(agendamento) : ResponseEntity.notFound().build();
    }


    // GET - buscar por UUID
    @Operation(summary = "Buscar agendamento por Uuid", description = "Retorna um agendamento com o UUID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamento encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Agendamento.class))),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado")
    })
    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<Agendamento> getAgendamentoUuid(@PathVariable String uuid) {
        Agendamento agendamento = this.service.getAgendamentoUuid(uuid);
        return agendamento != null ? ResponseEntity.ok(agendamento) : ResponseEntity.notFound().build();
    }


    // POST - criar agendamento
    @Operation(summary = "Criar um novo agendamento", description = "Cria um novo agendamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Agendamento criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Agendamento.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @PostMapping
    @Transactional
    public ResponseEntity<Agendamento> salvar(@RequestBody @Valid Agendamento agendamento, UriComponentsBuilder uriBuilder) {
        this.service.salvar(agendamento);
        URI uri = uriBuilder.path("/agendamento/{uuid}").buildAndExpand(agendamento.getUuid()).toUri();
        return ResponseEntity.created(uri).body(agendamento);
    }


    // PUT - atualizar agendamento
    @Operation(summary = "Atualizar agendamento por Id", description = "Atualiza os dados de um agendamento com id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamento atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Agendamento.class))),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado")
    })
    @PutMapping
    @Transactional
    public ResponseEntity<Agendamento> atualizar(@RequestBody Agendamento agendamento) {
        this.service.atualizar(agendamento);
        return ResponseEntity.ok(agendamento);
    }


    // DELETE - remover agendamento
    @Operation(summary = "Excluir agendamento", description = "Remove um agendamento de acordo com o ID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Agendamento removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        this.service.excluir(id);
        return ResponseEntity.noContent().build();
    }


    // POST - atualizar por UUID
    @Operation(summary = "Atualizar agendamento por Uuid", description = "Atualiza um agendamento usando o UUID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamento atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado")
    })
    @PostMapping("/uuid")
    public ResponseEntity<Void> atualizarPorUUID(@RequestBody Agendamento agendamento) {
        this.service.atualizarUUID(agendamento);
        return ResponseEntity.ok().build();
    }
}
