import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Biblioteca biblioteca = new Biblioteca();

        boolean sair = false;
        while (!sair) {
            System.out.println("\n=== Menu da Biblioteca ===");
            System.out.println("1. Adicionar livro");
            System.out.println("2. Adicionar cliente");
            System.out.println("3. Emprestar livro");
            System.out.println("4. Devolver livro");
            System.out.println("5. Sair");

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
