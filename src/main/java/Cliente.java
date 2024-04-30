import java.util.ArrayList;

public class Cliente extends Pessoa {
    private int idCliente;
    private ArrayList<Livro> livrosEmprestados;
    private int pontos;
    private String nivelRank;

    public Cliente(String nome, String endereco, String telefone, int idCliente) {
        super(nome, endereco, telefone);
        this.idCliente = idCliente;
        this.livrosEmprestados = new ArrayList<>();
        this.pontos = 0;
        this.nivelRank = "Bronze";
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public ArrayList<Livro> getLivrosEmprestados() {
        return livrosEmprestados;
    }

    public void setLivrosEmprestados(ArrayList<Livro> livrosEmprestados) {
        this.livrosEmprestados = livrosEmprestados;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public String getNivelRank() {
        return nivelRank;
    }

    public void setNivelRank(String nivelRank) {
        this.nivelRank = nivelRank;
    }

    public void emprestarLivro(Livro livro) {
        livrosEmprestados.add(livro);
        livro.setStatus("emprestado");
        pontos += 2; // Aumenta os pontos ao emprestar um livro
        atualizarNivelRank();
    }

    public void devolverLivro(Livro livro) {
        livrosEmprestados.remove(livro);
        livro.setStatus("disponível");
        pontos += 1; // Aumenta os pontos ao devolver um livro
        atualizarNivelRank();
    }

    private void atualizarNivelRank() {
        if (pontos >= 3 && pontos <= 10) {
            nivelRank = "Bronze";
        } else if (pontos >= 11 && pontos <= 15) {
            nivelRank = "Ouro";
        } else if (pontos > 15) {
            nivelRank = "Diamante";
        }
    }

    @Override
    public void imprimirDetalhes() {
        super.imprimirDetalhes();
        System.out.println("ID do Cliente: " + idCliente);
        System.out.println("Nível de Rank: " + nivelRank);
        System.out.println("Pontos: " + pontos);
        System.out.println("Livros Emprestados:");
        for (Livro livro : livrosEmprestados) {
            System.out.println("- " + livro.getTitulo());
        }
    }
}
