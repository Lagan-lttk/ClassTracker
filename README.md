# 🏫 Class Tracker

**Class Tracker** é um sistema desktop desenvolvido para otimizar o gerenciamento e a reserva de ambientes acadêmicos, como salas de aula e laboratórios. A aplicação também oferece controle de turmas, alunos e professores, utilizando uma arquitetura organizada em camadas com o padrão **DAO (Data Access Object)** e uma interface gráfica moderna construída com **JavaFX**.

---

## 📌 Sobre o Projeto

O objetivo do Class Tracker é facilitar a rotina acadêmica de instituições de ensino, permitindo que administradores, professores e alunos tenham acesso rápido às informações essenciais de salas, turmas, reservas e cronogramas.

O sistema centraliza dados acadêmicos e reduz conflitos de reserva, garantindo maior controle sobre a utilização dos ambientes disponíveis.

---

## 🚀 Funcionalidades Principais

### 👨‍🎓 Painel do Aluno

- Consulta rápida dos dados do aluno logado.
- Visualização da turma atual.
- Informações sobre sala, professor responsável e próximas aulas.
- Acompanhamento do cronograma acadêmico.

### 👨‍🏫 Painel do Professor

- Agenda de aulas organizada por blocos de horários.
- Visualização das turmas vinculadas.
- Consulta das salas reservadas para cada aula.
- Interface dinâmica para acompanhamento das atividades do dia.

### 🗓️ Gerenciamento de Reservas

- Cadastro de novas reservas.
- Edição e exclusão de reservas existentes.
- Listagem completa das reservas cadastradas.
- Controle de conflitos de horários.
- Validação de chaves estrangeiras para evitar inconsistências.

### 🛠️ Cadastros Administrativos

O sistema possui módulos CRUD completos para:

- **Alunos**
  - Cadastro com validação de matrícula.
  - Controle de curso e dados pessoais.

- **Professores**
  - Cadastro de formação acadêmica.
  - Registro de CPF, e-mail e telefone.

- **Salas e Laboratórios**
  - Controle de capacidade.
  - Organização por bloco e número.
  - Registro de disponibilidade.

- **Turmas**
  - Cadastro por curso, turno e tamanho da turma.

---

## 🧰 Tecnologias Utilizadas

| Tecnologia | Descrição |
|---|---|
| **Java 17+** | Linguagem principal do projeto |
| **JavaFX 26** | Construção da interface gráfica |
| **Scene Builder** | Design visual dos arquivos FXML |
| **MySQL 8.0+** | Banco de dados relacional |
| **JDBC** | Comunicação entre Java e MySQL |
| **MySQL Connector/J** | Driver de conexão com o banco |
| **CSS** | Estilização customizada dos componentes JavaFX |

---

## 📐 Arquitetura do Projeto

O projeto segue uma estrutura baseada nos padrões **MVC** e **DAO**, garantindo separação de responsabilidades, melhor organização do código e facilidade de manutenção.

```text
src/com/classtracker/
├── controller/     # Controladores JavaFX, regras de tela e navegação
├── dao/            # Classes responsáveis pelo acesso ao banco de dados
├── model/          # Entidades do sistema: Aluno, Professor, Sala, Turma, Reserva
├── util/           # Utilitários, como a conexão com o banco de dados
└── view/           # Arquivos FXML, imagens e recursos visuais
```

---

## 🗄️ Estrutura do Banco de Dados

O banco de dados utiliza chaves primárias, chaves únicas e chaves estrangeiras para garantir integridade referencial entre as tabelas.

### Tabelas principais

| Tabela | Finalidade |
|---|---|
| **aluno** | Armazena dados cadastrais dos alunos |
| **professor** | Registra docentes, formação e dados de contato |
| **sala** | Armazena informações das salas e laboratórios |
| **turma** | Registra cursos, turnos e tamanho das turmas |
| **aluno_por_turma** | Tabela associativa entre alunos e turmas |
| **reserva** | Centraliza as reservas de salas por professor, turma, data e horário |

---

## ⚙️ Como Rodar o Projeto

### ✅ Pré-requisitos

Antes de executar o projeto, certifique-se de ter instalado:

- **JDK 17** ou superior.
- **JavaFX SDK** configurado na IDE.
- **MySQL Server 8.0** ou superior.
- **MySQL Connector/J** adicionado ao projeto.
- IDE com suporte a JavaFX, como:
  - IntelliJ IDEA
  - Eclipse
  - NetBeans

---

## 🛢️ Configuração do Banco de Dados

Abra seu gerenciador MySQL, como o **MySQL Workbench**, e execute o script abaixo para criar o banco e as tabelas.

> ⚠️ Observação: o script abaixo já está organizado para respeitar a ordem correta das tabelas com chaves estrangeiras.

```sql
CREATE DATABASE IF NOT EXISTS classtracker;
USE classtracker;

CREATE TABLE aluno (
    id_aluno INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(45) NOT NULL,
    matricula VARCHAR(45) NOT NULL,
    data_de_nascimento DATE NOT NULL,
    cpf VARCHAR(45) NOT NULL,
    curso VARCHAR(45) NOT NULL,
    PRIMARY KEY (id_aluno),
    UNIQUE KEY uk_aluno_matricula (matricula)
);

CREATE TABLE professor (
    id_professor INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(45) NOT NULL,
    formacao VARCHAR(45) NOT NULL,
    cpf CHAR(11) NOT NULL,
    email VARCHAR(45) NOT NULL,
    telefone CHAR(11) NOT NULL,
    PRIMARY KEY (id_professor),
    UNIQUE KEY uk_professor_cpf (cpf),
    UNIQUE KEY uk_professor_email (email),
    UNIQUE KEY uk_professor_telefone (telefone)
);

CREATE TABLE sala (
    id_sala INT NOT NULL AUTO_INCREMENT,
    tipo VARCHAR(45) NOT NULL,
    capacidade INT NOT NULL,
    bloco VARCHAR(45) NOT NULL,
    numero INT NOT NULL,
    disponibilidade VARCHAR(45) NOT NULL,
    PRIMARY KEY (id_sala)
);

CREATE TABLE turma (
    id_turma INT NOT NULL AUTO_INCREMENT,
    curso VARCHAR(45) NOT NULL,
    tamanho INT NOT NULL,
    turno VARCHAR(45) NOT NULL,
    PRIMARY KEY (id_turma)
);

CREATE TABLE aluno_por_turma (
    id_turma INT NOT NULL,
    id_aluno INT NOT NULL,
    PRIMARY KEY (id_turma, id_aluno),
    KEY idx_aluno_por_turma_aluno (id_aluno),
    CONSTRAINT fk_aluno_por_turma_turma
        FOREIGN KEY (id_turma) REFERENCES turma (id_turma),
    CONSTRAINT fk_aluno_por_turma_aluno
        FOREIGN KEY (id_aluno) REFERENCES aluno (id_aluno)
);

CREATE TABLE reserva (
    id_reserva INT NOT NULL AUTO_INCREMENT,
    id_sala INT DEFAULT NULL,
    id_professor INT DEFAULT NULL,
    id_turma INT DEFAULT NULL,
    horario TIME NOT NULL,
    descricao VARCHAR(45) NOT NULL,
    dia DATE NOT NULL,
    disponibilidade VARCHAR(45) NOT NULL,
    PRIMARY KEY (id_reserva),
    KEY idx_reserva_sala (id_sala),
    KEY idx_reserva_professor (id_professor),
    KEY idx_reserva_turma (id_turma),
    CONSTRAINT fk_reserva_sala
        FOREIGN KEY (id_sala) REFERENCES sala (id_sala),
    CONSTRAINT fk_reserva_professor
        FOREIGN KEY (id_professor) REFERENCES professor (id_professor),
    CONSTRAINT fk_reserva_turma
        FOREIGN KEY (id_turma) REFERENCES turma (id_turma)
);
```

---

## 🧪 Dados de Teste

Antes de criar reservas, é importante popular previamente as tabelas **sala**, **professor** e **turma**, pois a tabela `reserva` depende dessas informações por meio de chaves estrangeiras.

Exemplo:

```sql
INSERT INTO professor (nome, formacao, cpf, email, telefone)
VALUES ('Carlos Silva', 'Mestre em Computação', '12345678901', 'carlos@email.com', '11999999999');

INSERT INTO sala (tipo, capacidade, bloco, numero, disponibilidade)
VALUES ('Laboratório', 30, 'Bloco A', 101, 'Disponível');

INSERT INTO turma (curso, tamanho, turno)
VALUES ('Análise e Desenvolvimento de Sistemas', 25, 'Noturno');
```

---

## 🔌 Configuração da Conexão Java

No arquivo:

```text
src/com/classtracker/util/DBConnection.java
```

ajuste as credenciais de acesso ao banco de dados local:

```java
private static final String URL = "jdbc:mysql://localhost:3306/classtracker";
private static final String USER = "seu_usuario";
private static final String PASSWORD = "sua_senha";
```

> ⚠️ Atenção: a porta padrão do MySQL é **3306**. Caso o seu MySQL esteja configurado em outra porta, altere a URL conforme necessário.

---

## ▶️ Execução do Projeto

1. Clone ou importe o projeto na sua IDE.
2. Configure o **JDK 17+**.
3. Adicione o **JavaFX SDK** ao projeto.
4. Inclua o **MySQL Connector/J** no Build Path.
5. Configure as VM Options do JavaFX, se necessário.
6. Execute a classe principal que estende `javafx.application.Application`.

Exemplo de VM Options:

```bash
--module-path "caminho/para/javafx-sdk/lib" --add-modules javafx.controls,javafx.fxml
```

---

## 📁 Organização Recomendada

```text
ClassTracker/
├── src/
│   └── com/
│       └── classtracker/
│           ├── controller/
│           ├── dao/
│           ├── model/
│           ├── util/
│           └── view/
├── lib/
│   └── mysql-connector-j.jar
├── database/
│   └── classtracker.sql
└── README.md
```

---

## 👥 Contribuidores

| Nome | Função |
|---|---|
| **Gustavo Almeida** | Desenvolvedor / Design de Interface |
| **Pablo Henrique** | Desenvolvedor |

---

## 📄 Licença

Este projeto foi desenvolvido para fins acadêmicos.

---

## ✨ Status do Projeto

🚧 Em desenvolvimento

Novas funcionalidades, melhorias visuais e ajustes de validação podem ser adicionados em versões futuras.
