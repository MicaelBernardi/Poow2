package br.ufsm.csi.Trabalho_POOW2.model.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClienteRepository  extends JpaRepository<Cliente, Long> {
    public Cliente findByUuid(UUID uuid);
    public void deleteByUuid(UUID uuid);
}
