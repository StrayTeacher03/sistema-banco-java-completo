# Sistema Banco Java Completo

Sistema bancário desktop em Java com interface gráfica Swing e persistência em PostgreSQL.

## Tecnologias
- Java 17+ (compatível com Java 21)
- Swing
- JDBC
- PostgreSQL
- Flyway para migração de banco de dados
- NetBeans (IDE sugerida)

## Estrutura do Projeto
- `src/banco/model` - classes de modelo
- `src/banco/interfaces` - contratos e interfaces
- `src/banco/dao` - acesso a dados e persistência
- `src/banco/service` - regras de negócio
- `src/banco/ui` - telas Swing
- `src/banco/resources/db/migration` - scripts SQL de migração
- `src/banco/app` - execução principal do sistema


## Como Executar
1. Abra o terminal na pasta raiz e rode o comando docker compose up -d
2. Depois abra o projeto no NetBeans.
3. Certifique-se de que `lib/postgresql-42.7.11.jar` e `lib/flyway-core-9.22.3.jar` estão no classpath.
4. Execute a classe `banco.app.SistemaBanco`.
5. Faça login com o usuário padrão criado pelo script de migração.

## Usuário Padrão
O script de inicialização insere um usuário administrador padrão. Atualize a senha após o primeiro login, se necessário.

## Classes Principais
- `ConexaoDB` - gerencia a conexão singleton com o PostgreSQL.
- `UsuarioDAO`, `ClienteDAO`, `ContaCorrenteDAO`, `ContaPoupancaDAO`, `TransacaoDAO` - persistência de dados.
- `UsuarioService`, `BancoService` - regras de negócio e operações bancárias.
- `TelaLogin`, `TelaMenuPrincipal`, `TelaGerenciarUsuarios`, `TelaCadastroUsuario`, `TelaCadastroCliente`, `TelaCadastroContaCorrente`, `TelaCadastroContaPoupanca`, `TelaOperacoes`, `TelaExtrato`, `TelaRelatorio` - interface gráfica.

## Estrutura de Classes
- `ContaBancaria` (abstrata)
  - `ContaCorrente`
  - `ContaPoupanca`

## Observações
- O projeto utiliza entradas e saídas apenas via interface Swing.
- Não são permitidos `System.out.println`, `Scanner`, `JOptionPane` ou leitura de terminal para interação com usuário.

# Aluno: Keslley Antonio
