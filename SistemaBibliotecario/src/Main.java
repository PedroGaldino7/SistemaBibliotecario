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
        gerenciadorlivro.carregarLivrosDoArquivo();
        int op;

        do{
        limparTela();
        System.out.println("=== Menu Biblioteca ===");
        System.out.println("1. Cadastrar livros:");
        System.out.println("2. Listar livros:");
        System.out.println("3. Excluir livros:");
        System.out.println("0. Sair:");
        System.out.print("Escolha uma opcao: ");
        op = sc.nextInt();
        sc.nextLine();


        switch (op) {
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
                    
                }else{
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

    }while(op != 0);
            sc.close();
}

}
