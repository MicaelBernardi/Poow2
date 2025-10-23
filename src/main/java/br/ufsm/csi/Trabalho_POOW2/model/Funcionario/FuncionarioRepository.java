package br.ufsm.csi.Trabalho_POOW2.model.Funcionario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    public Funcionario findByUuid(UUID uuid);
    public void deleteByUuid(UUID uuid);
}
