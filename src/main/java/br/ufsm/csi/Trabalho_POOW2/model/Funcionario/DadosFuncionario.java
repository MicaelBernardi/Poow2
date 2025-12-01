package br.ufsm.csi.Trabalho_POOW2.model.Funcionario;

public record DadosFuncionario(Long id, String email) {
    public DadosFuncionario(Funcionario funcionario) {
        this(funcionario.getId(), funcionario.getEmail());
    }
}
