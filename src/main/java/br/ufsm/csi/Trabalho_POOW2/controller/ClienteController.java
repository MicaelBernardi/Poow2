package br.ufsm.csi.Trabalho_POOW2.controller;

import br.ufsm.csi.Trabalho_POOW2.model.Cliente.Cliente;
import br.ufsm.csi.Trabalho_POOW2.service.ClienteService;
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
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }


    // GET - listar todos
    @Operation(summary = "Listar clientes", description = "Retorna lista de clientes ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)))
    })
    @GetMapping("/listar")
    public List<Cliente> listar() {
        return this.service.listar();
    }


    // GET - buscar por ID
    @Operation(summary = "Buscar cliente por Id", description = "Retorna um cliente com o ID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteId(@PathVariable Long id) {
        Cliente cliente = this.service.getCliente(id);
        return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
    }


    // GET - buscar por UUID
    @Operation(summary = "Buscar cliente por Uuid", description = "Retorna um cliente com o UUID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<Cliente> getClienteUuid(@PathVariable String uuid) {
        Cliente cliente = this.service.getClienteUuid(uuid);
        return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
    }


    // POST - criar cliente
    @Operation(summary = "Criar um novo cliente", description = "Cria um novo cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @PostMapping
    @Transactional
    public ResponseEntity<Cliente> salvar(@RequestBody @Valid Cliente cliente, UriComponentsBuilder uriBuilder) {
        this.service.salvar(cliente);
        URI uri = uriBuilder.path("/cliente/{uuid}").buildAndExpand(cliente.getUuid()).toUri();
        return ResponseEntity.created(uri).body(cliente);
    }


    // PUT - atualizar cliente
    @Operation(summary = "Atualizar cliente por Id", description = "Atualiza os dados de um cliente com o id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @PutMapping
    @Transactional
    public ResponseEntity<Cliente> atualizar(@RequestBody Cliente cliente) {
        this.service.atualizar(cliente);
        return ResponseEntity.ok(cliente);
    }


    // DELETE - remover cliente
    @Operation(summary = "Excluir cliente", description = "Remove um cliente de acordo com o ID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        this.service.excluir(id);
        return ResponseEntity.noContent().build();
    }


    // POST - atualizar por UUID
    @Operation(summary = "Atualizar cliente por Uuid", description = "Atualiza um cliente usando o Uuid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @PostMapping("/uuid")
    public ResponseEntity<Void> atualizarPorUUID(@RequestBody Cliente cliente) {
        this.service.atualizarUUID(cliente);
        return ResponseEntity.ok().build();
    }
}
