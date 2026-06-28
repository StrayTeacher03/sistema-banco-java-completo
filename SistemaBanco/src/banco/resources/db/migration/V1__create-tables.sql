CREATE TYPE perfil_usuario AS ENUM (
    'ADMIN',
    'OPERADOR'
);

CREATE TYPE tipo_trans AS ENUM (
    'CORRENTE',
    'POUPANCA'
);

CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    login VARCHAR(50) UNIQUE NOT NULL,
    senha VARCHAR(60) NOT NULL,
    nome VARCHAR(150) NOT NULL,
    perfil perfil_usuario NOT NULL
);

CREATE TABLE clientes (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    cpf CHAR(14) UNIQUE NOT NULL,
    telefone VARCHAR(21) NOT NULL
);

CREATE TABLE contas_correntes (
    id SERIAL PRIMARY KEY,
    numero_conta VARCHAR(20) UNIQUE NOT NULL,
    saldo NUMERIC(15,2) NOT NULL,
    limite_cheque NUMERIC(15,2) NOT NULL,
    cliente_id INT NOT NULL REFERENCES clientes(id)
);

CREATE TABLE contas_poupanca (
    id SERIAL PRIMARY KEY,
    numero_conta VARCHAR(20) UNIQUE NOT NULL,
    saldo NUMERIC(15,2) NOT NULL,
    taxa_rendimento NUMERIC(5,2) NOT NULL,
    cliente_id INT NOT NULL REFERENCES clientes(id)
);

CREATE TABLE transacoes (
    id SERIAL PRIMARY KEY,
    conta_id INT NOT NULL,
    tipo_conta tipo_trans NOT NULL,
    descricao TEXT NOT NULL,
    valor NUMERIC(15,2) NOT NULL,
    data_hora TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);