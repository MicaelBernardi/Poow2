package br.ufsm.csi.Trabalho_POOW2.controller;

import br.ufsm.csi.Trabalho_POOW2.model.Agendamento.Agendamento;
import br.ufsm.csi.Trabalho_POOW2.service.AgendamentoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    private final AgendamentoService service;

    public AgendamentoController(AgendamentoService service) {
        this.service = service;
    }

    // GET /agendamento (Pode filtrar opcionalmente por status via query param)
    // Ex: /agendamento?status=Agendado
    @GetMapping
    public List<Agendamento> listar(@RequestParam(required = false) String status) {
        if (status != null && !status.isEmpty()) {
            return this.service.listarPorStatus(status); // Crie este método no service se não tiver
        }
        return this.service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> getById(@PathVariable Long id) {
        Agendamento agendamento = this.service.getAgendamento(id);
        return agendamento != null ? ResponseEntity.ok(agendamento) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Agendamento> salvar(@RequestBody @Valid Agendamento agendamento, UriComponentsBuilder uriBuilder) {
        this.service.salvar(agendamento);
        URI uri = uriBuilder.path("/agendamento/{id}").buildAndExpand(agendamento.getId()).toUri();
        return ResponseEntity.created(uri).body(agendamento);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Agendamento> atualizar(@PathVariable Long id, @RequestBody Agendamento agendamento) {
        agendamento.setId(id);
        this.service.atualizar(agendamento);
        return ResponseEntity.ok(agendamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        this.service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    // PATCH /agendamento/{id}/finalizar (Endpoint Específico para mudar status)
    @PatchMapping("/{id}/finalizar")
    @Transactional
    public ResponseEntity<Void> finalizar(@PathVariable Long id) {
        this.service.finalizar(id); // Implemente no Service: setStatus("Finalizado")
        return ResponseEntity.ok().build();
    }
}