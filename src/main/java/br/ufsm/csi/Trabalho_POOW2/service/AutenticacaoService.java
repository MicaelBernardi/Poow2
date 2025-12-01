package br.ufsm.csi.Trabalho_POOW2.service;

import br.ufsm.csi.Trabalho_POOW2.model.Funcionario.Funcionario;
import br.ufsm.csi.Trabalho_POOW2.model.Funcionario.FuncionarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AutenticacaoService implements UserDetailsService {
    private final FuncionarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Funcionario funcionario = repository.findByEmail(email);

        if (funcionario == null) {
            throw new UsernameNotFoundException("Usuario nao encontrado");
        } else {
            UserDetails user = User.withUsername(funcionario.getEmail())
                    .password(funcionario.getSenha())
                    .authorities("USER")
                    .build();

            return user;
        }
    }
}
