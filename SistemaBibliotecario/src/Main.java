// javac -d bin src/*.java
// java -cp bin Main

import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Livro livro = new Livro();

        System.out.println("Detalhes do Livro:");
        System.out.println(livro);
        sc.close();
    }
}
