CREATE TABLE Cliente (
                         id SERIAL PRIMARY KEY,
                         nome VARCHAR(100) NOT NULL,
                         cpf VARCHAR(14) UNIQUE NOT NULL,
                         telefone VARCHAR(15)
);

CREATE TABLE Funcionario (
                             id SERIAL PRIMARY KEY,
                             nome VARCHAR(100) NOT NULL,
                             email VARCHAR(100) UNIQUE NOT NULL,
                             senha VARCHAR(100) NOT NULL
);

CREATE TABLE Servico (
                         id SERIAL PRIMARY KEY,
                         descricao VARCHAR(200) NOT NULL,
                         valor DOUBLE PRECISION NOT NULL
);

CREATE TABLE Agendamento (
                             id SERIAL PRIMARY KEY,
                             data DATE NOT NULL,
                             status VARCHAR(50),
                             cliente_id INT NOT NULL,
                             funcionario_id INT NOT NULL,
                             servico_id INT NOT NULL,
                             FOREIGN KEY (cliente_id) REFERENCES Cliente(id) ON DELETE CASCADE,
                             FOREIGN KEY (funcionario_id) REFERENCES Funcionario(id) ON DELETE CASCADE,
                             FOREIGN KEY (servico_id) REFERENCES Servico(id) ON DELETE CASCADE
);