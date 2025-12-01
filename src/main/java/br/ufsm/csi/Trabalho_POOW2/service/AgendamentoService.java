package br.ufsm.csi.Trabalho_POOW2.service;

import br.ufsm.csi.Trabalho_POOW2.model.Agendamento.Agendamento;
import br.ufsm.csi.Trabalho_POOW2.model.Agendamento.AgendamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AgendamentoService {

    private final AgendamentoRepository repository;

    public AgendamentoService(AgendamentoRepository repository) {
        this.repository = repository;
    }

    // === LISTAGEM (Com e Sem Filtro) ===

    public List<Agendamento> listar() {
        return repository.findAll();
    }

    // NOVO: Necessário para o filtro do Angular funcionar
    public List<Agendamento> listarPorStatus(String status) {
        return repository.findByStatusOrderByDataAsc(status);
    }

    public Agendamento getAgendamento(long id) {
        // .orElse(null) é mais seguro que .get() para evitar erro 500 se não existir
        return this.repository.findById(id).orElse(null);
    }

    // === PERSISTÊNCIA ===

    public Agendamento salvar(Agendamento agendamento) {
        // Se for novo cadastro, define status padrão
        if (agendamento.getId() == null || agendamento.getStatus() == null) {
            agendamento.setStatus("Agendado");
        }
        return repository.save(agendamento);
    }

    public Agendamento atualizar(Agendamento agendamento) {
        Agendamento a = this.repository.findById(agendamento.getId()).orElseThrow(
                () -> new RuntimeException("Agendamento não encontrado para atualização")
        );

        a.setData(agendamento.getData());
        // Só atualiza status se vier no objeto, senão mantém o antigo
        if (agendamento.getStatus() != null) {
            a.setStatus(agendamento.getStatus());
        }

        a.setCliente(agendamento.getCliente());
        a.setFuncionario(agendamento.getFuncionario());
        a.setServico(agendamento.getServico());

        return repository.save(a);
    }

    public void excluir(long id) {
        this.repository.deleteById(id);
    }

    // === NOVO: FINALIZAR AGENDAMENTO ===
    public void finalizar(Long id) {
        Agendamento agendamento = this.repository.findById(id).orElseThrow(
                () -> new RuntimeException("Agendamento não encontrado")
        );
        agendamento.setStatus("Finalizado");
        this.repository.save(agendamento);
    }

    // === MÉTODOS UUID (Mantidos do seu código original) ===

    public void atualizarUUID(Agendamento agendamento) {
        Agendamento a = this.repository.findByUuid(agendamento.getUuid());
        if (a != null) {
            a.setData(agendamento.getData());
            a.setStatus(agendamento.getStatus());
            a.setCliente(agendamento.getCliente());
            a.setFuncionario(agendamento.getFuncionario());
            a.setServico(agendamento.getServico());
            this.repository.save(a);
        }
    }

    public Agendamento getAgendamentoUuid(String uuid) {
        UUID uuidFormatado = UUID.fromString(uuid);
        return this.repository.findByUuid(uuidFormatado);
    }

    public void deletarUUID(String uuid) {
        this.repository.deleteByUuid(UUID.fromString(uuid));
    }
}