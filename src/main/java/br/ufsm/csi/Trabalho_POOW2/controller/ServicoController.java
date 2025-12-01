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

@CrossOrigin(origins = "http://localhost:4200") // <--- Necessário para o Angular
@RestController
@RequestMapping("/servico")
public class ServicoController {

    private final ServicoService service;

    public ServicoController(ServicoService service) {
        this.service = service;
    }

    // GET /servico (Removido /listar)
    @Operation(summary = "Listar Servicos", description = "Retorna a lista completa de servicos")
    @GetMapping
    public List<Servico> listar() {
        return this.service.listar();
    }

    // GET /servico/{id}
    @Operation(summary = "Buscar serviço por Id", description = "Retorna um serviço com o ID informado")
    @GetMapping("/{id}")
    public ResponseEntity<Servico> getServicoId(@PathVariable Long id) {
        Servico servico = this.service.getServico(id);
        return servico != null ? ResponseEntity.ok(servico) : ResponseEntity.notFound().build();
    }

    // POST /servico
    @Operation(summary = "Criar serviço", description = "Cria um novo serviço")
    @PostMapping
    @Transactional
    public ResponseEntity<Servico> salvar(@RequestBody @Valid Servico servico, UriComponentsBuilder uriBuilder) {
        this.service.salvar(servico);
        URI uri = uriBuilder.path("/servico/{uuid}").buildAndExpand(servico.getUuid()).toUri();
        return ResponseEntity.created(uri).body(servico);
    }

    // PUT /servico/{id} (Ajustado para receber ID na URL)
    @Operation(summary = "Atualizar serviço por Id", description = "Atualiza um serviço através do Id")
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Servico> atualizar(@PathVariable Long id, @RequestBody Servico servico) {
        servico.setId(id); // Garante consistência
        this.service.atualizar(servico);
        return ResponseEntity.ok(servico);
    }

    // DELETE /servico/{id}
    @Operation(summary = "Excluir serviço", description = "Remove um serviço pelo ID informado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        this.service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    // Métodos UUID mantidos como opcionais
    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<Servico> getServicoUuid(@PathVariable String uuid) {
        Servico servico = this.service.getServicoUuid(uuid);
        return servico != null ? ResponseEntity.ok(servico) : ResponseEntity.notFound().build();
    }

    @PostMapping("/uuid")
    public ResponseEntity<Servico> atualizarPorUUID(@RequestBody Servico servico) {
        this.service.atualizarUUID(servico);
        return ResponseEntity.ok().build();
    }
}