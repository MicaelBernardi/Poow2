INSERT INTO funcionario(nome, email, senha) VALUES ('teste', 't@1', 1);

INSERT INTO Cliente (nome, cpf, telefone) VALUES
                                              ('João Silva', '111.111.111-11', '(55) 99999-1111'),
                                              ('Maria Souza', '222.222.222-22', '(55) 99999-2222'),
                                              ('Pedro Oliveira', '333.333.333-33', '(55) 99999-3333'),
                                              ('Ana Santos', '444.444.444-44', '(55) 99999-4444'),
                                              ('Lucas Pereira', '555.555.555-55', '(55) 99999-5555');

INSERT INTO Funcionario (nome, email, senha) VALUES
                                                 ('Carlos Mendes', 'carlos@empresa.com', '123'),
                                                 ('Fernanda Lima', 'fernanda@empresa.com', 'abc'),
                                                 ('Rafael Gomes', 'rafael@empresa.com', 'senha1'),
                                                 ('Juliana Costa', 'juliana@empresa.com', 'senha2'),
                                                 ('Eduardo Rocha', 'eduardo@empresa.com', 'senha3');

INSERT INTO Servico (descricao, valor) VALUES
                                           ('Troca de óleo', 150.00),
                                           ('Revisão completa', 500.00),
                                           ('Alinhamento e balanceamento', 120.00),
                                           ('Troca de pneus', 800.00),
                                           ('Higienização interna', 200.00);

INSERT INTO Agendamento (data, status, cliente_id, funcionario_id, servico_id) VALUES
                                                                                   ('2025-01-10', 'Agendado', 1, 1, 1),
                                                                                   ('2025-01-12', 'Agendado', 2, 2, 3),
                                                                                   ('2025-01-15', 'Agendado', 3, 3, 2),
                                                                                   ('2025-01-20', 'Agendado', 4, 4, 5),
                                                                                   ('2025-01-22', 'Agendado', 5, 5, 4);
