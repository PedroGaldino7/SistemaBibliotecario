// javac -d bin src/*.java
// java -cp bin Main

import java.util.Scanner;

public class Main {

    public static void limparTela(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
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
            op = sc.nextInt();
            sc.nextLine();

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
                        opLivro = sc.nextInt();
                        sc.nextLine();


                        switch (opLivro) {
                            case 1:
                                limparTela();
                                System.out.println("=== Cadastro de Livros ===");
                                System.out.print("Codigo: ");
                                String codigo = sc.nextLine();
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
                                limparTela();
                                System.out.println("=== Lista de Livros ===");
                                gerenciadorlivro.listarLivros();
                                System.out.println("Pressione Enter para continuar...");
                                sc.nextLine();
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
                        opUsuario = sc.nextInt();
                        sc.nextLine();

                        switch (opUsuario) {
                            case 1:
                                limparTela();
                                System.out.println("=== Cadastro de Usuarios ===");
                                System.out.println("Digite os dados do usuario:");
                                System.out.println("Matricula:");
                                String matricula = sc.nextLine();
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
                                System.out.println("Construindo...");
                                System.out.println("Pressione Enter para continuar...");
                                sc.nextLine();
                                gerenciadorusuario.listarUsuarios();

                                // if (gerenciadorusuario.getUsuarios().isEmpty()) {
                                //     System.out.println("Pressione Enter para continuar...");
                                //     sc.nextLine();
                                //     break;
                                // } else {
                                //     System.out.print("Digite o nome do usuário a ser excluído: ");
                                //     String nomeExcluir = sc.nextLine();
                                //     boolean excluido = gerenciadorusuario.excluirUsuario(nomeExcluir);
                                //     if (excluido) {
                                //         System.out.println("Usuário excluído com sucesso!");
                                //     } else {
                                //         System.out.println("Usuário não encontrado.");
                                //     }
                                //     System.out.println("Pressione Enter para continuar...");
                                //     sc.nextLine();
                                //     break;
                                // }

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
                    // gerenciadorlivro.salvarLivrosNoArquivo();
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }while(op != 0);
        sc.close();
}

}
