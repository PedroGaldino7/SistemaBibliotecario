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
        Livro livro = new Livro();
        Usuario usuario = new Usuario();

        limparTela();
        System.out.println("=== Menu Biblioteca ===");
        System.out.println("Cadastrar livros:");
        sc.close();
    }

}
