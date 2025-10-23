package br.ufsm.csi.Trabalho_POOW2.model.Agendamento;

import br.ufsm.csi.Trabalho_POOW2.model.Cliente.Cliente;
import br.ufsm.csi.Trabalho_POOW2.model.Funcionario.Funcionario;
import br.ufsm.csi.Trabalho_POOW2.model.Servico.Servico;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "agendamento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid", columnDefinition = "uuid", updatable = false, nullable = false)
    @UuidGenerator
    private UUID uuid;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "servico_id")
    private Servico servico;

    @NotBlank
    private String status;
}

