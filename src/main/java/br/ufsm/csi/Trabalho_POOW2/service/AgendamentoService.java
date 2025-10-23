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

    public Agendamento salvar(Agendamento agendamento) {
        return repository.save(agendamento);
    }

    public List<Agendamento> listar() {
        return repository.findAll();
    }

    public Agendamento getAgendamento(long id) {
        return this.repository.findById(id).get();
    }

    public void excluir(long id) {
        this.repository.deleteById(id);
    }

    public Agendamento atualizar(Agendamento agendamento) {
        Agendamento a = this.repository.getReferenceById(agendamento.getId());
        a.setData(agendamento.getData());
        a.setStatus(agendamento.getStatus());
        a.setCliente(agendamento.getCliente());
        a.setFuncionario(agendamento.getFuncionario());
        a.setServico(agendamento.getServico());
        return repository.save(a);
    }

    public void atualizarUUID(Agendamento agendamento) {
        Agendamento a = this.repository.findByUuid(agendamento.getUuid());
        a.setData(agendamento.getData());
        a.setStatus(agendamento.getStatus());
        a.setCliente(agendamento.getCliente());
        a.setFuncionario(agendamento.getFuncionario());
        a.setServico(agendamento.getServico());
        this.repository.save(a);
    }

    public Agendamento getAgendamentoUuid(String uuid) {
        UUID uuidFormatado = UUID.fromString(uuid);
        return this.repository.findByUuid(uuidFormatado);
    }

    public void deletarUUID(String uuid) {
        this.repository.deleteByUuid(UUID.fromString(uuid));
    }
}
