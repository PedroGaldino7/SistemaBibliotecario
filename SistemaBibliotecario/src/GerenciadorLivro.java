import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorLivro {

    private List<Livro> livros;

    public GerenciadorLivro() {
        this.livros = new ArrayList<>();
    }

    public void cadastrarLivro(Livro livro) {
        livros.add(livro);
        salvarLivroEmArquivo(livro);
    }

    public void salvarLivroEmArquivo(Livro livro) {
        try (FileWriter writer = new FileWriter("livros.txt", true)) {
            writer.write(
                livro.getCodigo() + ";" +
                livro.getTitulo() + ";" +
                livro.getAutor() + ";" +
                livro.isDisponivel() + "\n"
            );

        } catch (IOException e) {
            System.out.println("Erro ao salvar livro: " + e.getMessage());
        }
    }

    public void listarLivros() {
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }

        for (Livro l : livros) {
            System.out.println(
                l.getCodigo() + " - " +
                l.getTitulo() + " | " +
                l.getAutor() + " | " +
                (l.isDisponivel() ? "Disponivel" : "Emprestado")
            );
        }
    }

    public void carregarLivrosDoArquivo() {
        File arquivo = new File("livros.txt");

        if (!arquivo.exists() || arquivo.length() == 0) {
            return;
        }

        livros.clear();

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");

                if (dados.length == 4) {
                    Livro livro = new Livro(dados[0], dados[1], dados[2]);
                    livro.setDisponivel(Boolean.parseBoolean(dados[3]));
                    livros.add(livro);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar livros: " + e.getMessage());
        }
    }

    public boolean excluirLivro(String codigo) {
    boolean removido = livros.removeIf(
        l -> l.getCodigo().equals(codigo)
    );

    if (removido) {
        if (livros.isEmpty()) {
            apagarArquivo();
        } else {
            atualizarArquivo();
        }
    }

    return removido;
}


public void apagarArquivo() {
    File arquivo = new File("livros.txt");

    if (arquivo.exists()) {
        if (arquivo.delete()) {

        } else {}
    }
}


    public void atualizarArquivo() {
    try (FileWriter writer = new FileWriter("livros.txt")) { // SEM append
        for (Livro l : livros) {
            writer.write(
                l.getCodigo() + ";" +
                l.getTitulo() + ";" +
                l.getAutor() + ";" +
                l.isDisponivel() + "\n"
            );
        }
    } catch (IOException e) {
        System.out.println("Erro ao atualizar arquivo: " + e.getMessage());
    }
}



    public List<Livro> getLivros() {
        return livros;
    }
}
