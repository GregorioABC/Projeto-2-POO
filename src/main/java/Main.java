import java.util.ArrayList;
import java.util.Scanner;

class Pessoa {
    private String nome;
    private String endereco;
    private String telefone;

    public Pessoa(String nome, String endereco, String telefone) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void imprimirDetalhes() {
        System.out.println("Nome: " + nome);
        System.out.println("Endereço: " + endereco);
        System.out.println("Telefone: " + telefone);
    }
}

class Cliente extends Pessoa {
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

class Livro {
    private String titulo;
    private String autor;
    private int idLivro;
    private String status;

    public Livro(String titulo, String autor, int idLivro, String status) {
        this.titulo = titulo;
        this.autor = autor;
        this.idLivro = idLivro;
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

class Biblioteca {
    private ArrayList<Livro> listaLivros;
    private ArrayList<Pessoa> listaClientes;

    public Biblioteca() {
        listaLivros = new ArrayList<>();
        listaClientes = new ArrayList<>();
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

    public ArrayList<Livro> getListaLivros() {
        return listaLivros;
    }

    public ArrayList<Pessoa> getListaClientes() {
        return listaClientes;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Biblioteca biblioteca = new Biblioteca();

        boolean sair = false;
        while (!sair) {
            System.out.println("\n=== Biblioteca ===");
            System.out.println("1. Adicionar livro");
            System.out.println("2. Adicionar cliente");
            System.out.println("3. Emprestar livro");
            System.out.println("4. Devolver livro");
            System.out.println("5. Listar clientes atuais");
            System.out.println("6. Sair");

            System.out.print("\nEscolha uma opção: ");
            int opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    adicionarLivro(biblioteca, scanner);
                    break;
                case 2:
                    adicionarCliente(biblioteca, scanner);
                    break;
                case 3:
                    emprestarLivro(biblioteca, scanner);
                    break;
                case 4:
                    devolverLivro(biblioteca, scanner);
                    break;
                case 5:
                    listarClientes(biblioteca);
                    break;
                case 6:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        System.out.println("Encerrando o programa.");
        scanner.close();
    }

    private static void adicionarLivro(Biblioteca biblioteca, Scanner scanner) {
        System.out.println("\nAdicionar livro à biblioteca:");
        System.out.print("Título do livro: ");
        String tituloLivro = scanner.nextLine();

        System.out.print("Autor do livro: ");
        String autorLivro = scanner.nextLine();

        int idLivro;
        do {
            System.out.print("ID do livro: ");
            idLivro = Integer.parseInt(scanner.nextLine());
        } while (!verificarIdLivroUnico(biblioteca, idLivro));

        Livro livro = new Livro(tituloLivro, autorLivro, idLivro, "disponível");
        biblioteca.adicionarLivro(livro);
        System.out.println("Livro adicionado com sucesso!");
    }

    private static boolean verificarIdLivroUnico(Biblioteca biblioteca, int idLivro) {
        for (Livro livro : biblioteca.getListaLivros()) {
            if (livro.getIdLivro() == idLivro) {
                System.out.println("Este ID já está em uso. Por favor, insira outro ID.");
                return false;
            }
        }
        return true;
    }

    private static void adicionarCliente(Biblioteca biblioteca, Scanner scanner) {
        System.out.println("\nAdicionar cliente à biblioteca:");
        System.out.print("Nome do cliente: ");
        String nomeCliente = scanner.nextLine();

        System.out.print("Endereço do cliente: ");
        String enderecoCliente = scanner.nextLine();

        System.out.print("Telefone do cliente: ");
        String telefoneCliente = scanner.nextLine();

        int idCliente;
        do {
            System.out.print("ID do cliente: ");
            idCliente = Integer.parseInt(scanner.nextLine());
        } while (!verificarIdClienteUnico(biblioteca, idCliente));

        Cliente cliente = new Cliente(nomeCliente, enderecoCliente, telefoneCliente, idCliente);
        biblioteca.adicionarCliente(cliente);
        System.out.println("Cliente adicionado com sucesso!");
    }

    private static boolean verificarIdClienteUnico(Biblioteca biblioteca, int idCliente) {
        for (Pessoa pessoa : biblioteca.getListaClientes()) {
            if (pessoa instanceof Cliente) {
                Cliente cliente = (Cliente) pessoa;
                if (cliente.getIdCliente() == idCliente) {
                    System.out.println("Este ID já está em uso. Por favor, insira outro ID.");
                    return false;
                }
            }
        }
        return true;
    }

    private static void emprestarLivro(Biblioteca biblioteca, Scanner scanner) {
        System.out.println("\nEmpréstimo de livro:");
        System.out.print("ID do cliente: ");
        int idCliente = Integer.parseInt(scanner.nextLine());

        System.out.print("ID do livro: ");
        int idLivro = Integer.parseInt(scanner.nextLine());

        Cliente cliente = encontrarClientePorId(biblioteca, idCliente);
        Livro livro = encontrarLivroPorId(biblioteca, idLivro);

        if (cliente != null && livro != null) {
            biblioteca.emprestarLivro(cliente, livro);
        } else {
            System.out.println("Cliente ou livro não encontrado.");
        }
    }

    private static void devolverLivro(Biblioteca biblioteca, Scanner scanner) {
        System.out.println("\nDevolução de livro:");
        System.out.print("ID do cliente: ");
        int idCliente = Integer.parseInt(scanner.nextLine());

        System.out.print("ID do livro: ");
        int idLivro = Integer.parseInt(scanner.nextLine());

        Cliente cliente = encontrarClientePorId(biblioteca, idCliente);
        Livro livro = encontrarLivroPorId(biblioteca, idLivro);

        if (cliente != null && livro != null) {
            biblioteca.devolverLivro(cliente, livro);
        } else {
            System.out.println("Cliente ou livro não encontrado.");
        }
    }

    private static void listarClientes(Biblioteca biblioteca) {
        System.out.println("\nLista de Clientes Atuais:");
        for (Pessoa pessoa : biblioteca.getListaClientes()) {
            if (pessoa instanceof Cliente) {
                Cliente cliente = (Cliente) pessoa;
                System.out.println("\nID do Cliente: " + cliente.getIdCliente());
                cliente.imprimirDetalhes();
            }
        }
    }

    private static Cliente encontrarClientePorId(Biblioteca biblioteca, int idCliente) {
        for (Pessoa pessoa : biblioteca.getListaClientes()) {
            if (pessoa instanceof Cliente) {
                Cliente cliente = (Cliente) pessoa;
                if (cliente.getIdCliente() == idCliente) {
                    return cliente;
                }
            }
        }
        return null;
    }

    private static Livro encontrarLivroPorId(Biblioteca biblioteca, int idLivro) {
        for (Livro livro : biblioteca.getListaLivros()) {
            if (livro.getIdLivro() == idLivro) {
                return livro;
            }
        }
        return null;
    }
}
