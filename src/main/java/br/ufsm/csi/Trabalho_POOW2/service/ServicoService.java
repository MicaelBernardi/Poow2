package br.ufsm.csi.Trabalho_POOW2.service;

import br.ufsm.csi.Trabalho_POOW2.model.Servico.Servico;
import br.ufsm.csi.Trabalho_POOW2.model.Servico.ServicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ServicoService {

    private final ServicoRepository repository;

    public ServicoService(ServicoRepository repository) {
        this.repository = repository;
    }

    public Servico salvar(Servico servico) {
        return repository.save(servico);
    }

    public List<Servico> listar() {
        return repository.findAll();
    }

    public Servico getServico(long id) {
        return this.repository.findById(id).get();
    }

    public void excluir(long id) {
        this.repository.deleteById(id);
    }

    public Servico atualizar(Servico servico) {
        Servico s = this.repository.getReferenceById(servico.getId());
        s.setDescricao(servico.getDescricao());
        s.setValor(servico.getValor());
        return repository.save(s);
    }

    public void atualizarUUID(Servico servico) {
        Servico s = this.repository.findByUuid(servico.getUuid());
        s.setDescricao(servico.getDescricao());
        s.setValor(servico.getValor());
        this.repository.save(s);
    }

    public Servico getServicoUuid(String uuid) {
        UUID uuidFormatado = UUID.fromString(uuid);
        return this.repository.findByUuid(uuidFormatado);
    }

    public void deletarUUID(String uuid) {
        this.repository.deleteByUuid(UUID.fromString(uuid));
    }
}
