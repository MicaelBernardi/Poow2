package br.ufsm.csi.Trabalho_POOW2.service;

import br.ufsm.csi.Trabalho_POOW2.model.Funcionario.Funcionario;
import br.ufsm.csi.Trabalho_POOW2.model.Funcionario.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FuncionarioService {

    private final FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public Funcionario salvar(Funcionario funcionario) {
        return repository.save(funcionario);
    }

    public List<Funcionario> listar() {
        return repository.findAll();
    }

    public Funcionario getFuncionario(long id) {
        return this.repository.findById(id).get();
    }

    public void excluir(long id) {
        this.repository.deleteById(id);
    }

    public Funcionario atualizar(Funcionario funcionario) {
        Funcionario f = this.repository.getReferenceById(funcionario.getId());
        f.setNome(funcionario.getNome());
        f.setEmail(funcionario.getEmail());
        f.setSenha(funcionario.getSenha());
        return repository.save(f);
    }

    public void atualizarUUID(Funcionario funcionario) {
        Funcionario f = this.repository.findByUuid(funcionario.getUuid());
        f.setNome(funcionario.getNome());
        f.setEmail(funcionario.getEmail());
        f.setSenha(funcionario.getSenha());
        this.repository.save(f);
    }

    public Funcionario getFuncionarioUuid(String uuid) {
        UUID uuidFormatado = UUID.fromString(uuid);
        return this.repository.findByUuid(uuidFormatado);
    }

    public void deletarUUID(String uuid) {
        this.repository.deleteByUuid(UUID.fromString(uuid));
    }
}
