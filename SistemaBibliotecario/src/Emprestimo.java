import java.time.LocalDate;

public class Emprestimo {

    private String matriculaUsuario;
    private String codigoLivro;

    private LocalDate dataEmprestimo;
    private LocalDate dataLimiteDevolucao;
    private LocalDate dataDevolucao;

    private boolean ativo;

    // ===============================
    // CONSTRUTOR PARA NOVO EMPRÉSTIMO
    // ===============================
    public Emprestimo(String matriculaUsuario, String codigoLivro, int diasPraDevolver) {
        this.matriculaUsuario = matriculaUsuario;
        this.codigoLivro = codigoLivro;

        this.dataEmprestimo = LocalDate.now();
        this.dataLimiteDevolucao = dataEmprestimo.plusDays(diasPraDevolver);
        this.dataDevolucao = null;

        this.ativo = true;
    }

    // ===============================
    // CONSTRUTOR PARA CARREGAR DO ARQUIVO
    // ===============================
    public Emprestimo(
        String matriculaUsuario,
        String codigoLivro,
        LocalDate dataEmprestimo,
        LocalDate dataLimiteDevolucao,
        LocalDate dataDevolucao,
        boolean ativo
    ) {
        this.matriculaUsuario = matriculaUsuario;
        this.codigoLivro = codigoLivro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataLimiteDevolucao = dataLimiteDevolucao;
        this.dataDevolucao = dataDevolucao;
        this.ativo = ativo;
    }

    // ===============================
    // GETTERS
    // ===============================
    public String getMatriculaUsuario() {
        return matriculaUsuario;
    }

    public String getCodigoLivro() {
        return codigoLivro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataLimiteDevolucao() {
        return dataLimiteDevolucao;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    // ===============================
    // DEVOLUÇÃO
    // ===============================
    public void registrarDevolucao() {
        this.dataDevolucao = LocalDate.now();
        this.ativo = false;
    }
}
