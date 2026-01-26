// javac -d bin src/*.java
// java -cp bin Main

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void limparTela(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

public static int lerIntSeguro(Scanner sc) {
    while (true) {
        try {
            int valor = sc.nextInt();
            sc.nextLine();
            return valor;
        } catch (InputMismatchException e) {
            sc.nextLine();
            limparTela();
            System.out.println("Entrada invalida! Digite apenas numeros.");
            System.out.println("Pressione Enter para voltar ao menu...");
            sc.nextLine();
            return -1;
        }
    }
}


    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        GerenciadorLivro gerenciadorlivro = new GerenciadorLivro();
        GerenciadorUsuario gerenciadorusuario = new GerenciadorUsuario();
        gerenciadorlivro.carregarLivrosDoArquivo();
        gerenciadorusuario.carregarUsuariosDoArquivo();
        int op;

        do{
            limparTela();
            System.out.println("=== Sistema Bibliotecario ===");
            System.out.println("1. Gerenciar livros");
            System.out.println("2. Gerenciar usuarios");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opcao: ");
            op = lerIntSeguro(sc);
            if (op == -1) continue;

            switch (op) {
                case 1:
                    int opLivro;
                    do{
                        limparTela();
                        System.out.println("=== Menu livros ===");
                        System.out.println("1. Cadastrar livros:");
                        System.out.println("2. Listar livros:");
                        System.out.println("3. Excluir livros:");
                        System.out.println("0. Sair:");
                        System.out.print("Escolha uma opcao: ");
                        opLivro = lerIntSeguro(sc);
                        if (opLivro == -1) continue;

                        switch (opLivro) {
                            case 1:
                                limparTela();
                                System.out.println("=== Cadastro de Livros ===");
                                System.out.print("Codigo: ");
                                String codigo = sc.nextLine();

                                if (gerenciadorlivro.livroExiste(codigo)) {
                                    System.out.println("Livro ja cadastrado.");
                                    System.out.println("Pressione Enter para voltar pro menu...");
                                    sc.nextLine();
                                    break;
                                }

                                System.out.print("Titulo: ");
                                String titulo = sc.nextLine();
                                System.out.print("Autor: ");
                                String autor = sc.nextLine();
                                Livro livro = new Livro(codigo, titulo, autor);
                                gerenciadorlivro.cadastrarLivro(livro);
                                System.out.println("Livro cadastrado com sucesso!");
                                System.out.println("Pressione Enter para continuar...");
                                sc.nextLine();  
                                break;
                            
                            case 2:
                                int listarOp;
                                do{
                                limparTela();
                                System.out.println("=== Lista de Livros ===");
                                System.out.println("1. Livros disponiveis");
                                System.out.println("2. livros emprestados");
                                System.out.println("3. todos os livros");
                                System.out.println("0. Voltar ao menu");
                                System.out.print("Escolha uma opcao: ");
                                listarOp = lerIntSeguro(sc);
                                if (listarOp == -1) continue;
                                
                                switch (listarOp) {
                                    case 1:
                                        limparTela();
                                        System.out.println("=== Livros Disponiveis ===");

                                        if (gerenciadorlivro.getLivrosDisponiveis().isEmpty()) {
                                            System.out.println("Nenhum livro disponivel.");
                                            System.out.println("Pressione Enter para voltar ao menu...");
                                            sc.nextLine();
                                            break;
                                        }
                                        for (Livro l : gerenciadorlivro.getLivrosDisponiveis()) {
                                            System.out.println(l.getCodigo() + " - " + l.getTitulo() + " | " + l.getAutor());
                                        }
                                        System.out.println("Pressione Enter para voltar ao menu...");
                                        sc.nextLine();
                                        break;

                                    case 2:
                                        limparTela();
                                        System.out.println("=== Livros Emprestados ===");

                                        if (gerenciadorlivro.getLivrosEmprestados().isEmpty()) {
                                            System.out.println("Nenhum livro emprestado.");
                                            System.out.println("Pressione Enter para voltar ao menu...");
                                            sc.nextLine();
                                            break;
                                        }

                                        for (Livro l : gerenciadorlivro.getLivrosEmprestados()) {
                                            System.out.println(l.getCodigo() + " - " + l.getTitulo() + " | " + l.getAutor());
                                        }
                                        System.out.println("Pressione Enter para voltar ao menu...");
                                        sc.nextLine();
                                        break;

                                    case 3:
                                        limparTela();
                                        System.out.println("=== Todos os Livros ===");
                                        gerenciadorlivro.listarLivros();
                                        System.out.println("Pressione Enter para voltar ao menu...");
                                        sc.nextLine();
                                        break;

                                    case 0:
                                        limparTela();

                                    default:
                                        System.out.println("Opcao invalida!");
                                        break;
                                }
                            }while(listarOp != 0);
                                break;

                            case 3:
                                limparTela();
                                System.out.println("=== Exclusao de Livros ===");
                                gerenciadorlivro.listarLivros();
                                
                                if (gerenciadorlivro.getLivros().isEmpty()) {
                                    System.out.println("Pressione Enter para continuar...");
                                    sc.nextLine();
                                    break;
                                }else {
                                    System.out.print("Digite o codigo do livro a ser excluido: ");
                                    String codigoExcluir = sc.nextLine();
                                    boolean excluido = gerenciadorlivro.excluirLivro(codigoExcluir);
                                    if (excluido) {
                                        System.out.println("Livro excluido com sucesso!");
                                    } else {
                                        System.out.println("Livro nao encontrado.");
                                    }
                                    System.out.println("Pressione Enter para continuar...");
                                    sc.nextLine();
                                    break;
                                }

                                case 0:
                                    System.out.println("Saindo...");
                                    break;
                                
                                default:
                                    System.out.println("Opção inválida!");
                                    break;
                        }
                    }while(opLivro != 0);
                    break;

                case 2:
                    int opUsuario;
                    do {
                        limparTela();
                        System.out.println("=== Gerenciar Usuarios ===");
                        System.out.println("1. Cadastrar usuario");
                        System.out.println("2. Listar usuarios");
                        System.out.println("3. Excluir usuario");
                        System.out.println("0. Voltar");
                        System.out.print("Escolha uma opcao: ");
                        opUsuario = lerIntSeguro(sc);
                        if (opUsuario == -1) continue;

                        switch (opUsuario) {
                            case 1:
                                limparTela();
                                System.out.println("=== Cadastro de Usuarios ===");
                                System.out.println("Digite os dados do usuario:");
                                System.out.print("Matricula: ");
                                String matricula = sc.nextLine();

                                if (gerenciadorusuario.usuarioExiste(matricula)) {
                                    System.out.println("Usuario ja cadastrado.");
                                    System.out.println("Pressione Enter para voltar pro menu...");
                                    sc.nextLine();
                                    break;
                                }

                                System.out.print("Nome: ");
                                String nome = sc.nextLine();
                                System.out.print("Email: ");
                                String email = sc.nextLine();
                                Usuario usuario = new Usuario(matricula, nome, email);
                                gerenciadorusuario.cadastrarUsuario(usuario);
                                System.out.println("Usuario cadastrado com sucesso!");
                                System.out.println("Pressione Enter para continuar...");
                                sc.nextLine();
                                break;

                            case 2:
                                limparTela();
                                System.out.println("=== Lista de Usuarios ===");
                                gerenciadorusuario.listarUsuarios();
                                System.out.println("Pressione Enter para continuar...");
                                sc.nextLine();
                                break;

                            case 3:
                                limparTela();
                                System.out.println("=== Exclusão de Usuarios ===");
                                gerenciadorusuario.listarUsuarios();

                                if (gerenciadorusuario.getUsuarios().isEmpty()) {
                                    System.out.println("Pressione Enter para continuar...");
                                    sc.nextLine();
                                    break;
                                } else {
                                    System.out.print("Digite a matricula do usuario a ser excluido: ");
                                    String matriculaExcluir = sc.nextLine();
                                    boolean excluido = gerenciadorusuario.excluirUsuario(matriculaExcluir);
                                    if (excluido) {
                                        System.out.println("Usuario excluido com sucesso!");
                                    } else {
                                        System.out.println("Usuario nao encontrado.");
                                    }
                                    System.out.println("Pressione Enter para continuar...");
                                    sc.nextLine();
                                    break;
                                }

                            case 0:
                                System.out.println("Saindo...");
                                break;

                            default:
                                System.out.println("Opção inválida!");
                                break;
                        }
                    } while (opUsuario != 0);
                    break;

                case 0:
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }while(op != 0);
        sc.close();
}

}
