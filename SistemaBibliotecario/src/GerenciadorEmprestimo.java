import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorEmprestimo {

    private List<Emprestimo> emprestimos;

    private GerenciadorLivro gerenciadorLivro;
    private GerenciadorUsuario gerenciadorUsuario;

    public GerenciadorEmprestimo(GerenciadorLivro gl, GerenciadorUsuario gu) {
        this.emprestimos = new ArrayList<>();
        this.gerenciadorLivro = gl;
        this.gerenciadorUsuario = gu;

        carregarEmprestimosDoArquivo();
    }

    // ===============================
    // EMPRESTAR LIVRO
    // ===============================
    public boolean emprestarLivro(String matricula, String codigoLivro, int diasPraDevolver) {

        if (!gerenciadorUsuario.usuarioExiste(matricula) ||
            !gerenciadorLivro.livroExiste(codigoLivro)) {
            return false;
        }

        if (!gerenciadorLivro.livroDisponivel(codigoLivro)) {
            return false;
        }

        Emprestimo novoEmprestimo = new Emprestimo(matricula, codigoLivro, diasPraDevolver);
        emprestimos.add(novoEmprestimo);

        gerenciadorLivro.marcarLivroComoIndisponivel(codigoLivro);
        salvarEmprestimoEmArquivo(novoEmprestimo);

        return true;
    }

    // ===============================
    // DEVOLVER LIVRO
    // ===============================
    public boolean devolverLivro(String codigoLivro) {

        for (Emprestimo e : emprestimos) {
            if (e.getCodigoLivro().equals(codigoLivro) && e.isAtivo()) {

                e.registrarDevolucao();
                gerenciadorLivro.marcarLivroComoDisponivel(codigoLivro);

                reescreverArquivoEmprestimos();
                return true;
            }
        }

        return false;
    }

    // ===============================
    // ARQUIVO
    // ===============================
    public void carregarEmprestimosDoArquivo() {
        File arquivo = new File("emprestimos.txt");
        if (!arquivo.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;

            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");

                if (dados.length == 6) {
                    Emprestimo emprestimo = new Emprestimo(
                        dados[0],
                        dados[1],
                        LocalDate.parse(dados[2]),
                        LocalDate.parse(dados[3]),
                        dados[4].equals("null") ? null : LocalDate.parse(dados[4]),
                        Boolean.parseBoolean(dados[5])
                    );

                    emprestimos.add(emprestimo);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar empréstimos: " + e.getMessage());
        }
    }

    public void salvarEmprestimoEmArquivo(Emprestimo emprestimo) {
        try (FileWriter writer = new FileWriter("emprestimos.txt", true)) {
            writer.write(
                emprestimo.getMatriculaUsuario() + ";" +
                emprestimo.getCodigoLivro() + ";" +
                emprestimo.getDataEmprestimo() + ";" +
                emprestimo.getDataLimiteDevolucao() + ";" +
                (emprestimo.getDataDevolucao() != null ? emprestimo.getDataDevolucao() : "null") + ";" +
                emprestimo.isAtivo() + "\n"
            );
        } catch (IOException e) {
            System.out.println("Erro ao salvar empréstimo: " + e.getMessage());
        }
    }

    public void reescreverArquivoEmprestimos() {
        try (FileWriter writer = new FileWriter("emprestimos.txt")) {
            for (Emprestimo e : emprestimos) {
                writer.write(
                    e.getMatriculaUsuario() + ";" +
                    e.getCodigoLivro() + ";" +
                    e.getDataEmprestimo() + ";" +
                    e.getDataLimiteDevolucao() + ";" +
                    (e.getDataDevolucao() != null ? e.getDataDevolucao() : "null") + ";" +
                    e.isAtivo() + "\n"
                );
            }
        } catch (IOException e) {
            System.out.println("Erro ao atualizar arquivo: " + e.getMessage());
        }
    }

    public void listarEmprestimos() {
        for (Emprestimo e : emprestimos) {
            System.out.println(
                "Usuario: " + e.getMatriculaUsuario() +
                ", Livro: " + e.getCodigoLivro() +
                ", Data Emprestimo: " + e.getDataEmprestimo() +
                ", Data Limite Devolucao: " + e.getDataLimiteDevolucao() +
                ", Data Devolucao: " + (e.getDataDevolucao() != null ? e.getDataDevolucao() : "N/A") +
                ", Ativo: " + e.isAtivo()
            );
        }
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }
}
