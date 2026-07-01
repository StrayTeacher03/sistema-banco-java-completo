CREATE TYPE perfil_usuario AS ENUM (
    'ADMIN',
    'OPERADOR'
);

CREATE TYPE tipo_trans AS ENUM (
    'CORRENTE',
    'POUPANCA'
);

CREATE TABLE IF NOT EXISTS usuarios (
    id SERIAL PRIMARY KEY,
    login VARCHAR(50) UNIQUE NOT NULL,
    senha VARCHAR(128) NOT NULL,
    nome VARCHAR(150) NOT NULL,
    perfil perfil_usuario NOT NULL
);

ALTER TABLE IF EXISTS usuarios ALTER COLUMN senha TYPE VARCHAR(128);

CREATE TABLE IF NOT EXISTS clientes (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    cpf CHAR(14) UNIQUE NOT NULL,
    telefone VARCHAR(21) NOT NULL
);

CREATE TABLE IF NOT EXISTS contas_correntes (
    id SERIAL PRIMARY KEY,
    numero_conta VARCHAR(20) UNIQUE NOT NULL,
    saldo NUMERIC(15,2) NOT NULL,
    limite_cheque NUMERIC(15,2) NOT NULL,
    cliente_id INT NOT NULL REFERENCES clientes(id)
);

CREATE TABLE IF NOT EXISTS contas_poupanca (
    id SERIAL PRIMARY KEY,
    numero_conta VARCHAR(20) UNIQUE NOT NULL,
    saldo NUMERIC(15,2) NOT NULL,
    taxa_rendimento NUMERIC(5,2) NOT NULL,
    cliente_id INT NOT NULL REFERENCES clientes(id)
);

CREATE TABLE IF NOT EXISTS transacoes (
    id SERIAL PRIMARY KEY,
    conta_id INT NOT NULL,
    tipo_conta tipo_trans NOT NULL,
    descricao TEXT NOT NULL,
    valor NUMERIC(15,2) NOT NULL,
    data_hora TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);