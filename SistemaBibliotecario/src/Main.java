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
                break;

            case 2:
                limparTela();
                System.out.println("=== Lista de Livros ===");
                gerenciadorlivro.listarLivros();
                System.out.println("Pressione Enter para continuar...");
                sc.nextLine();
                break;

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
