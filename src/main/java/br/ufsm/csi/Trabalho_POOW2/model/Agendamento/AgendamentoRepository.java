package br.ufsm.csi.Trabalho_POOW2.model.Agendamento;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    public Agendamento findByUuid(UUID uuid);
    public void deleteByUuid(UUID uuid);
}
