package br.ufsm.csi.Trabalho_POOW2.model.Servico;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
    public Servico findByUuid(UUID uuid);
    public void deleteByUuid(UUID uuid);
}
