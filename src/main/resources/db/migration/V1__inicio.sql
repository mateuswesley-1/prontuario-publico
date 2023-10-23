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



