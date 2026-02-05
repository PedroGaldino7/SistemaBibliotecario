import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorUsuario {

    private List<Usuario> usuarios;

    public GerenciadorUsuario() {
        this.usuarios = new ArrayList<>();
    }

    public void cadastrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        salvarUsuarioEmArquivo(usuario);
    }

    public void salvarUsuarioEmArquivo(Usuario usuario) {
        try (FileWriter writer = new FileWriter("usuarios.txt", true)) {
            writer.write(
                usuario.getMatricula() + ";" +
                usuario.getNome() + ";" +
                usuario.getEmail() + "\n"
            );

        } catch (IOException e) {
            System.out.println("Erro ao salvar usuario: " + e.getMessage());
        }
    }

    public void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuario cadastrado.");
            return;
        }

        for (Usuario u : usuarios) {
            System.out.println(
                u.getMatricula() + " - " +
                u.getNome() + " | " +
                u.getEmail()
            );
        }
    }

    public void carregarUsuariosDoArquivo() {
        File arquivo = new File("usuarios.txt");

        if (!arquivo.exists() || arquivo.length() == 0) {
            return;
        }

        usuarios.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 3) {
                    String matricula = partes[0];
                    String nome = partes[1];
                    String email = partes[2];
                    Usuario usuario = new Usuario(matricula, nome, email);
                    usuarios.add(usuario);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar usuarios: " + e.getMessage());
        }
    }

    public boolean excluirUsuario(String matricula) {
        boolean removido = usuarios.removeIf(
            u -> u.getMatricula().equals(matricula)
        );

        if (removido) {
            if (usuarios.isEmpty()) {
                apagarArquivo();
            } else {
                atualizarArquivo();
            }
        }

        return removido;
    }


    public void apagarArquivo() {
    File arquivo = new File("usuarios.txt");

        if (arquivo.exists()) {
            if (arquivo.delete()) {
            } else {
                
            }
        }
    }


    public void atualizarArquivo() {
        try (FileWriter writer = new FileWriter("usuarios.txt")) { // SEM append
            for (Usuario u : usuarios) {
                writer.write(
                    u.getMatricula() + ";" +
                    u.getNome() + ";" +
                    u.getEmail() + "\n"
                );
            }
        } catch (IOException e) {
            System.out.println("Erro ao atualizar arquivo: " + e.getMessage());
        }
    }

    public boolean usuarioExiste(String matricula) {
        return usuarios.stream()
            .anyMatch(u -> u.getMatricula().equals(matricula));
    }

    public String buscarNomeUsuarioPorMatricula(String matricula) {
        for (Usuario u : usuarios) {
            if (u.getMatricula().equals(matricula)) {
                return u.getNome();
            }
        }
        return "Usuário não encontrado";
    }


    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}
