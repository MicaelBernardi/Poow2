package br.ufsm.csi.Trabalho_POOW2.model.Servico;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "servico")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid", columnDefinition = "uuid", updatable = false, nullable = false)
    @UuidGenerator
    private UUID uuid;

    @NotBlank
    private String descricao;

    @NotNull
    private double valor;
}

