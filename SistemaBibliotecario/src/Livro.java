public class Livro {
    private String codigo;
    private String titulo;
    private String autor;
    private boolean disponivel;

    public Livro() {
        this.codigo = "000";
        this.titulo = "Titulo Padrao";
        this.autor = "Autor Padrao";
        this.disponivel = true;
    }
    
    public Livro(String codigo, String titulo, String autor) {
        this.codigo = codigo;
        this.titulo = titulo;   
        this.autor = autor;
        this.disponivel = true;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return "Codigo: " + codigo + ", Titulo: " + titulo + ", Autor: " + autor + ", Disponivel: " + (disponivel ? "Sim" : "Nao");
    }
}
