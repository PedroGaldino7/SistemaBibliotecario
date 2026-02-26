# 📚 Sistema Bibliotecário em Java

Sistema de gerenciamento bibliotecário desenvolvido em Java com foco em Programação Orientada a Objetos.

O sistema permite o controle de livros, usuários e empréstimos, utilizando persistência em arquivos locais.

---

## 🚀 Funcionalidades

### 📘 Gerenciamento de Livros
- Cadastrar livro
- Listar livros disponíveis
- Listar livros emprestados
- Listar todos os livros
- Excluir livro
- Controle automático de disponibilidade

### 👤 Gerenciamento de Usuários
- Cadastrar usuário
- Listar usuários
- Excluir usuário
- Validação de matrícula duplicada

### 🔄 Gerenciamento de Empréstimos
- Emprestar livro
- Devolver livro
- Listar empréstimos
- Definição de prazo de devolução
- Controle automático de status do livro

---

## 🧠 Conceitos aplicados

- Programação Orientada a Objetos (POO)
- Encapsulamento
- Separação de responsabilidades
- Manipulação de arquivos
- Tratamento de exceções (`InputMismatchException`)
- Estruturas de repetição e controle
- Validação de dados
- Organização em múltiplas classes

---


## 📂 Estrutura do Projeto

src/

├── Main.java

├── Livro.java

├── Usuario.java

├── Emprestimo.java

├── GerenciadorLivro.java

├── GerenciadorUsuario.java

└── GerenciadorEmprestimo.java



---

## ▶️ Como Executar

### Indo ate a pasta:

- cd .\SistemaBibliotecario\

### Compilar:

- javac -d bin src/*.java

### Executar:

- java -cp bin Main


---

## 💾 Persistência

O sistema utiliza arquivos `.txt` para salvar:

- Livros cadastrados
- Usuários cadastrados
- Empréstimos realizados

Os dados são carregados automaticamente ao iniciar o sistema.

---

## 🛠 Tecnologias Utilizadas

- Java
- Programação Orientada a Objetos
- Console Application
- Manipulação de Arquivos

---

## 📌 Melhorias Futuras

- Interface gráfica (JavaFX ou Swing)
- Banco de dados (MySQL ou PostgreSQL)
- Sistema de multa por atraso
- Login de administrador
- Relatórios de empréstimos
- Refatoração da `Main` para reduzir responsabilidade

---

## 👨‍💻 Autor

Pedro Galdino  
Estudante de Engenharia de Software  
Projeto desenvolvido para prática e consolidação de conceitos de Java e POO.


> Projeto desenvolvido como exercício prático para consolidar conceitos de arquitetura básica de sistemas orientados a objetos.