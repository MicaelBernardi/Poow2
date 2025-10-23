package br.ufsm.csi.Trabalho_POOW2.model.Cliente;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid", columnDefinition = "uuid", updatable = false, nullable = false)
    @UuidGenerator
    private UUID uuid;

    @NotBlank
    private String nome;

    @NotBlank
    @Size(max = 14)
    private String cpf;

    @NotBlank
    private String telefone;
}
