import service.ContatoDAO;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ContatoDAO contatoDAO = new ContatoDAO();
        Scanner scanner = new Scanner(System.in);
        int escolha;

        do {
            System.out.println("##################");
            System.out.println("##### AGENDA #####");
            System.out.println("##################");

            System.out.println(">>>> Contatos <<<<");
            System.out.println("Id | Nome | Telefones");
            contatoDAO.exibirContatos();

            System.out.println(">>>> Menu <<<<");
            System.out.println("1 - Adicionar Contato");
            System.out.println("2 - Remover Contato");
            System.out.println("3 - Editar Contato");
            System.out.println("4 - Sair");

            System.out.println(">>>> Opção <<<<");
            escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    contatoDAO.adicionarContato();
                    break;
                case 2:
                    contatoDAO.removerContato();
                    break;
                case 3:
                    contatoDAO.editarContato();
                    break;
                case 4:
                    break;
                default:
                    System.out.println(">>>> Escolha Inválida <<<<");
            }
        } while (escolha != 4);


        scanner.close();
    }
}