import java.util.ArrayList;

public class Biblioteca {
    private ArrayList<Livro> listaLivros;
    private ArrayList<Cliente> listaClientes;

    public Biblioteca() {
        listaLivros = new ArrayList<>();
        listaClientes = new ArrayList<>();
    }

    public ArrayList<Livro> getListaLivros() {
        return listaLivros;
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void adicionarLivro(Livro livro) {
        listaLivros.add(livro);
    }

    public void removerLivro(Livro livro) {
        listaLivros.remove(livro);
    }

    public void adicionarCliente(Cliente cliente) {
        listaClientes.add(cliente);
    }

    public void removerCliente(Cliente cliente) {
        listaClientes.remove(cliente);
    }

    public void emprestarLivro(Cliente cliente, Livro livro) {
        if (listaLivros.contains(livro) && livro.getStatus().equals("disponível")) {
            if (verificarLimiteEmprestimo(cliente)) {
                cliente.emprestarLivro(livro);
                System.out.println("Livro emprestado com sucesso!");
                System.out.println("Informações do Cliente:");
                cliente.imprimirDetalhes();
            } else {
                System.out.println("Limite de empréstimo atingido para o nível de rank atual.");
            }
        } else {
            System.out.println("Livro não disponível para empréstimo.");
        }
    }

    public void devolverLivro(Cliente cliente, Livro livro) {
        if (cliente.getLivrosEmprestados().contains(livro)) {
            cliente.devolverLivro(livro);
            System.out.println("Livro devolvido com sucesso!");
        } else {
            System.out.println("Cliente não possui este livro emprestado.");
        }
    }

    private boolean verificarLimiteEmprestimo(Cliente cliente) {
        if (cliente.getNivelRank().equals("Bronze") && cliente.getLivrosEmprestados().size() < 5) {
            return true;
        } else if (cliente.getNivelRank().equals("Ouro") && cliente.getLivrosEmprestados().size() < 10) {
            return true;
        } else if (cliente.getNivelRank().equals("Diamante")) {
            return true;
        }
        return false;
    }
}
