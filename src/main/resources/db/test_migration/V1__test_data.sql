CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE funcionario_tb(
                               id VARCHAR(36) PRIMARY KEY DEFAULT uuid_generate_v4(),
                               created_at TIMESTAMP NOT NULL,
                               deleted_at TIMESTAMP,
                               updated_at TIMESTAMP NOT NULL,
                               cpf VARCHAR(11) NOT NULL,
                               nome VARCHAR(50) NOT NULL,
                               data_nascimento DATE NOT NULL,
                               endereco VARCHAR(100) NOT NULL,
                               email VARCHAR(50) UNIQUE NOT NULL,
                               cargo VARCHAR(20) NOT NULL
);

CREATE TABLE credenciais_tb(
                               id varchar(36) PRIMARY KEY DEFAULT uuid_generate_v4(),
                               username varchar(50) UNIQUE,
                               password varchar(255),
                               created_at TIMESTAMP NOT NULL,
                               deleted_at TIMESTAMP,
                               updated_at TIMESTAMP NOT NULL
);

CREATE TABLE atestado_tb
(
    id VARCHAR(36) PRIMARY KEY DEFAULT uuid_generate_v4(),
    created_at TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP,
    updated_at TIMESTAMP NOT NULL,
    duracao integer NOT NULL CHECK ( duracao >= 1 AND duracao <= 30),
    descricao VARCHAR(250)
);

CREATE TABLE medicamento_tb
(
    id varchar(36) PRIMARY KEY DEFAULT uuid_generate_v4(),
    created_at TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP,
    updated_at TIMESTAMP NOT NULL,
    nome VARCHAR(50) NOT NULL,
    dose real NOT NULL
);

CREATE TABLE medico_tb
(
    id varchar(36) PRIMARY KEY DEFAULT uuid_generate_v4(),
    funcionario_id varchar(36),
    FOREIGN KEY (funcionario_id) REFERENCES funcionario_tb (id),
    created_at     TIMESTAMP NOT NULL,
    deleted_at     TIMESTAMP,
    updated_at     TIMESTAMP NOT NULL,
    crm            integer  NOT NULL,
    especialidade  VARCHAR(50)
);

CREATE TABLE consulta_tb
(
    id varchar(36) PRIMARY KEY DEFAULT uuid_generate_v4(),
    medico_id varchar(36),
    paciente_id varchar(36),
    atestado_id varchar(36),
    anamnese varchar(250),
    created_at TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP,
    updated_at TIMESTAMP NOT NULL,
    FOREIGN KEY (medico_id) REFERENCES medico_tb (id),
    FOREIGN KEY (paciente_id) REFERENCES funcionario_tb (id),
    FOREIGN KEY (atestado_id) REFERENCES atestado_tb (id)

);

CREATE TABLE prescricao_tb
(
    id varchar(36) PRIMARY KEY DEFAULT uuid_generate_v4(),
    created_at     TIMESTAMP NOT NULL,
    deleted_at     TIMESTAMP,
    updated_at     TIMESTAMP NOT NULL,
    qtd_dias       integer NOT NULL,
    frequencia_horas integer NOT NULL,
    medicamento_id varchar(36),
    consulta_id varchar(36),
    FOREIGN KEY (medicamento_id) REFERENCES medicamento_tb (id),
    FOREIGN KEY (consulta_id) REFERENCES  consulta_tb (id)
);


INSERT INTO funcionario_tb (id, created_at, updated_at, cpf, nome, data_nascimento, endereco, email, cargo)
VALUES ('funcionario_id', NOW(), NOW(), '12345678901', 'Nome Funcionário', '1990-01-01', 'Endereço Funcionário', 'email@example.com', 'Cargo Funcionário');

INSERT INTO credenciais_tb (id,created_at, updated_at, username, password)
VALUES ('credenciais_id', NOW(), NOW(), 'username', 'senha');

INSERT INTO atestado_tb (id, created_at, updated_at, duracao, descricao)
VALUES ('atestado_id', NOW(), NOW(), 7, 'Descrição do Atestado');

INSERT INTO medicamento_tb (id, created_at, updated_at, nome, dose)
VALUES ('medicamento_id', NOW(), NOW(), 'Nome Medicamento', 2.5);

INSERT INTO medico_tb (id, created_at, updated_at, funcionario_id, crm, especialidade)
VALUES ('medico_id', NOW(), NOW(), (SELECT id FROM funcionario_tb WHERE cpf = '12345678901'), 12345, 'Especialidade Médica');

INSERT INTO consulta_tb (id, created_at, updated_at, medico_id, paciente_id, atestado_id, anamnese)
VALUES ('consulta_id', NOW(), NOW(), (SELECT id FROM medico_tb WHERE crm = 12345), (SELECT id FROM funcionario_tb WHERE cpf = '12345678901'), (SELECT id FROM atestado_tb WHERE descricao = 'Descrição do Atestado'), 'Anamnese da Consulta');

INSERT INTO prescricao_tb (id, created_at, updated_at, qtd_dias, frequencia_horas, medicamento_id, consulta_id)
VALUES ('prescricao_id', NOW(), NOW(), 10, 6, (SELECT id FROM medicamento_tb WHERE nome = 'Nome Medicamento'), (SELECT id FROM consulta_tb WHERE anamnese = 'Anamnese da Consulta'));