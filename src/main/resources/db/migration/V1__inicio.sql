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
   funcionario_id varchar(36),
   FOREIGN KEY (funcionario_id) REFERENCES funcionario_tb(id),
   username varchar(50) UNIQUE,
   password varchar(255),
   created_at TIMESTAMP NOT NULL,
   deleted_at TIMESTAMP,
   updated_at TIMESTAMP NOT NULL
);