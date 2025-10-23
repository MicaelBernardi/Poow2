package br.ufsm.csi.Trabalho_POOW2.controller;

import br.ufsm.csi.Trabalho_POOW2.model.Servico.Servico;
import br.ufsm.csi.Trabalho_POOW2.service.ServicoService;
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
@RequestMapping("/servico")
public class ServicoController {

    private final ServicoService service;

    public ServicoController(ServicoService service) {
        this.service = service;
    }


    // GET - Listar Serviços
    @Operation(summary = "Listar Servicos", description = "Retorna a lista completa de servicos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Servico.class)))
    })
    @GetMapping("/listar")
    public List<Servico> listar() {
        return this.service.listar();
    }


    //GET - Busca por Id
    @Operation(summary = "Buscar serviço por Id", description = "Retorna um serviço com o ID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Serviço encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Servico.class))),
            @ApiResponse(responseCode = "404", description = "Serviço não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Servico> getServicoId(@PathVariable Long id) {
        Servico servico = this.service.getServico(id);
        return servico != null ? ResponseEntity.ok(servico) : ResponseEntity.notFound().build();
    }


    //GET - Busca por Uuid
    @Operation(summary = "Buscar serviço por Uuid", description = "Retorna um serviço com UUID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Serviço encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Servico.class))),
            @ApiResponse(responseCode = "404", description = "Serviço não encontrado")
    })
    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<Servico> getServicoUuid(@PathVariable String uuid) {
        Servico servico = this.service.getServicoUuid(uuid);
        return servico != null ? ResponseEntity.ok(servico) : ResponseEntity.notFound().build();
    }


    // POST - Criar Serviço
    @Operation(summary = "Criar serviço", description = "Cria um novo serviço")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Serviço criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Servico.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @PostMapping
    @Transactional
    public ResponseEntity salvar(@RequestBody @Valid Servico servico, UriComponentsBuilder uriBuilder) {
        this.service.salvar(servico);
        URI uri = uriBuilder.path("/servico/{uuid}").buildAndExpand(servico.getUuid()).toUri();
        return ResponseEntity.created(uri).body(servico);
    }


    // PUT - atualizar por Id
    @Operation(summary = "Atualizar serviço por Id", description = "Atualiza um serviço através do Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Serviço atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Servico.class))),
            @ApiResponse(responseCode = "404", description = "Serviço não encontrado")
    })
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody Servico servico) {
        this.service.atualizar(servico);
        return ResponseEntity.ok(servico);
    }


    // DELETE - remover servico
    @Operation(summary = "Excluir serviço", description = "Remove um serviço pelo ID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Serviço removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Serviço não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity remover(@PathVariable Long id) {
        this.service.excluir(id);
        return ResponseEntity.noContent().build();
    }


    //POST - atualizar por Uuid
    @Operation(summary = "Atualizar serviço por Uuid", description = "Atualiza serviço com o Uuid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Serviço atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Serviço não encontrado")
    })
    @PostMapping("/uuid")
    public ResponseEntity<Servico> atualizarPorUUID(@RequestBody Servico servico) {
        this.service.atualizarUUID(servico);
        return ResponseEntity.ok().build();
    }
}
