package br.ufsm.csi.Trabalho_POOW2.model.Funcionario;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "funcionario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid", columnDefinition = "uuid", updatable = false, nullable = false)
    @UuidGenerator
    private UUID uuid;

    @NotBlank
    private String nome;

    @NotBlank
    @Email(message = "Email inválido")
    private String email;

    @NotBlank
    private String senha;

}
