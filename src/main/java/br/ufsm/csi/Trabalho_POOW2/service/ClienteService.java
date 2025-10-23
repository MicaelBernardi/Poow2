package br.ufsm.csi.Trabalho_POOW2.service;

import br.ufsm.csi.Trabalho_POOW2.model.Cliente.Cliente;
import br.ufsm.csi.Trabalho_POOW2.model.Cliente.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public Cliente salvar(Cliente cliente) {
        return repository.save(cliente);
    }

    public List<Cliente> listar() {
        return repository.findAll();
    }

    public Cliente getCliente(long id) {
        return this.repository.findById(id).get();
    }

    public void excluir(long id) {
        this.repository.deleteById(id);
    }

    public Cliente atualizar(Cliente cliente) {
        Cliente c = this.repository.getReferenceById(cliente.getId());
        c.setNome(cliente.getNome());
        c.setCpf(cliente.getCpf());
        c.setTelefone(cliente.getTelefone());
        return repository.save(c);
    }

    public void atualizarUUID(Cliente cliente) {
        Cliente c = this.repository.findByUuid(cliente.getUuid());
        c.setNome(cliente.getNome());
        c.setCpf(cliente.getCpf());
        c.setTelefone(cliente.getTelefone());
        this.repository.save(c);
    }

    public Cliente getClienteUuid(String uuid) {
        UUID uuidformatado = UUID.fromString(uuid);
        return this.repository.findByUuid(uuidformatado);
    }

    public void deletarUUID(String uuid) {
        this.repository.deleteByUuid(UUID.fromString(uuid));
    }

}
