# 🏫 Class Tracker

O **Class Tracker** é um sistema desktop desenvolvido para otimizar o gerenciamento e a reserva de ambientes acadêmicos (salas e laboratórios), controle de turmas, alunos e professores. O projeto utiliza uma arquitetura baseada no padrão **DAO (Data Access Object)** para persistência de dados e uma interface gráfica moderna construída com **JavaFX**.

---

## 🚀 Funcionalidades Principais

* **Painel do Aluno (Dashboard):** Consulta rápida de dados do aluno logado, informações da sala, turma atual, professor responsável e cronograma das próximas aulas.
* **Painel do Professor:** Agenda de aulas dinâmica dividida por blocos de horários, turmas e salas correspondentes.
* **Gerenciamento de Reservas (CRUD):** Criação, edição, exclusão e listagem de reservas de salas evitando conflitos de horários e duplicidade de chaves.
* **Cadastros Administrativos:** Módulos completos para inserção, atualização e remoção de:
  * Alunos (com validação de matrícula e curso).
  * Professores (com detalhes de formação e contato).
  * Salas/Laboratórios (com controle de capacidade e bloco).

---

## 🛠️ Tecnologias Utilizadas

O projeto foi construído utilizando as seguintes tecnologias:

* **Linguagem:** Java 17+
* **Interface Gráfica:** JavaFX 26 (com Scene Builder para design FXML)
* **Banco de Dados:** MySQL 8.0+
* **Driver de Conexão:** MySQL Connector/J (JDBC)
* **Estilização:** CSS customizado para componentes JavaFX

---

## 📐 Estrutura do Projeto (Padrão MVC/DAO)

O código está organizado de forma modular para garantir manutenibilidade e separação de responsabilidades:

```text
src/com/classtracker/
├── controller/     # Controladores do JavaFX (Regras de negócio da tela e navegação)
├── model/          # Classes de Entidade (Aluno, Professor, Sala, Reserva)
├── util/           # Utilitários (Classe de conexão com o Banco de Dados)
└── view/           # Arquivos .fxml (Interfaces visuais) e imagens
```
🗄️ Estrutura do Banco de Dados

O banco de dados possui integridade referencial rigorosa utilizando chaves estrangeiras (FOREIGN KEY) para impedir inconsistências:

    aluno: Armazena dados cadastrais e chaves únicas como matrícula.

    professor: Contém registros dos docentes e suas respectivas formações.

    sala: Registra a infraestrutura física disponível.

    turma: Agrupa os cursos, períodos e tamanhos das turmas.

    aluno_por_turma: Tabela associativa (N:M) que vincula alunos às suas respectivas turmas.

    reserva: Tabela central que unifica uma sala, um professor, uma turma, uma data e um horário específico.

🏁 Como Rodar o Projeto
Pré-requisitos

    JDK 17 ou superior instalado.

    IDE de sua preferência (IntelliJ IDEA recomendada ou Eclipse) com suporte a JavaFX configurado.

    MySQL Server rodando localmente.

1. Configuração do Banco de Dados

Abra o seu gerenciador MySQL (ex: Workbench) e execute o script de criação das tabelas:
SQL

CREATE DATABASE classtracker;
USE classtracker;

```
CREATE TABLE `aluno` (
  `id_aluno` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `matricula` varchar(45) NOT NULL,
  `data_de_nascimento` date NOT NULL,
  `cpf` varchar(45) NOT NULL,
  `curso` varchar(45) NOT NULL,
  PRIMARY KEY (`id_aluno`),
  UNIQUE KEY `matricula` (`matricula`)
);

CREATE TABLE `aluno_por_turma` (
  `id_turma` int NOT NULL,
  `id_aluno` int NOT NULL,
  PRIMARY KEY (`id_turma`,`id_aluno`),
  KEY `id_aluno` (`id_aluno`),
  CONSTRAINT `aluno_por_turma_ibfk_1` FOREIGN KEY (`id_turma`) REFERENCES `turma` (`id_turma`),
  CONSTRAINT `aluno_por_turma_ibfk_2` FOREIGN KEY (`id_aluno`) REFERENCES `aluno` (`id_aluno`)
;

CREATE TABLE `alunos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` char(80) NOT NULL,
  `idade` int NOT NULL,
  PRIMARY KEY (`id`)
) 

CREATE TABLE `professor` (
  `id_professor` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `formação` varchar(45) NOT NULL,
  `cpf` char(11) NOT NULL,
  `email` varchar(45) NOT NULL,
  `telefone` char(11) NOT NULL,
  PRIMARY KEY (`id_professor`),
  UNIQUE KEY `cpf` (`cpf`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `telefone` (`telefone`)
);

CREATE TABLE `reserva` (
  `id_reserva` int NOT NULL AUTO_INCREMENT,
  `id_sala` int DEFAULT NULL,
  `id_professor` int DEFAULT NULL,
  `id_turma` int DEFAULT NULL,
  `horário` time NOT NULL,
  `descrição` varchar(45) NOT NULL,
  `dia` date NOT NULL,
  `disponibilidade` varchar(45) NOT NULL,
  PRIMARY KEY (`id_reserva`),
  KEY `id_sala` (`id_sala`),
  KEY `id_professor` (`id_professor`),
  KEY `id_turma` (`id_turma`),
  CONSTRAINT `reserva_ibfk_1` FOREIGN KEY (`id_sala`) REFERENCES `sala` (`id_sala`),
  CONSTRAINT `reserva_ibfk_2` FOREIGN KEY (`id_professor`) REFERENCES `professor` (`id_professor`),
  CONSTRAINT `reserva_ibfk_3` FOREIGN KEY (`id_turma`) REFERENCES `turma` (`id_turma`)
);

CREATE TABLE `sala` (
  `id_sala` int NOT NULL AUTO_INCREMENT,
  `tipo` varchar(45) NOT NULL,
  `capacidade` int NOT NULL,
  `bloco` varchar(45) NOT NULL,
  `numero` int NOT NULL,
  `disponibilidade` varchar(45) NOT NULL,
  PRIMARY KEY (`id_sala`)
;

CREATE TABLE `turma` (
  `id_turma` int NOT NULL AUTO_INCREMENT,
  `curso` varchar(45) NOT NULL,
  `tamanho` int NOT NULL,
  `turno` varchar(45) NOT NULL,
  PRIMARY KEY (`id_turma`)
)
```
Nota: Certifique-se de popular previamente as tabelas sala, professor e turma com dados de teste antes de efetuar uma reserva para não violar as restrições de Foreign Key.

2. Configurar a Conexão no Java

Abra o arquivo src/com/classtracker/util/DBConnection.java e ajuste as credenciais de acesso ao seu banco de dados local:
Java

private static final String URL = "jdbc:mysql://localhost:3606/classtracker";
private static final String USER = "seu_usuario";
private static final String PASSWORD = "sua_senha";

3. Execução

Importe o projeto na sua IDE, certifique-se de que as bibliotecas do JavaFX estão incluídas no Build Path / VM Options e execute a classe principal (geralmente a classe que estende javafx.application.Application).

    🤝 Contribuidores
    Gustavo Almeida - Desenvolvedor / Design de Interface;
    Pablo Henrique - Desenvolvedor;
