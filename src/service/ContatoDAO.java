package service;

import model.Contato;
import model.Telefone;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ContatoDAO {
    private String arquivo = "contatos.dat";

    public void adicionarContato() {
        List<Contato> contatos = carregarContatos();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Insira o ID do contato: ");
        Long id = scanner.nextLong();

        System.out.print("Insira o nome do contato: ");
        String nome = scanner.next();

        System.out.print("Insira o sobrenome do contato: ");
        String sobrenome = scanner.next();

        List<Telefone> telefones = adicionarTelefone();

        // Criar um objeto do contato
        Contato contato = new Contato();
        contato.setId(id);
        contato.setNome(nome);
        contato.setSobrenome(sobrenome);
        contato.setTelefones(telefones);

        //Validando existencia de contato com ID semelhante
        boolean contatoExiste = false;
        boolean telefoneExiste = false;
        for (Contato c : contatos) {
            if (Objects.equals(c.getId(), contato.getId())) {
                contatoExiste = true;
                break;
            }

            for (Telefone telefone2 : c.getTelefones()) {
                for (Telefone telefone1 : telefones) {
                    // valida ids iguais
                    if (Objects.equals(telefone1.getId(), telefone2.getId())) {
                        telefoneExiste = true;
                    }

                    // concatena DDD com telefone e valida
                    if (telefone1.getDdd().concat(telefone1.getNumero().toString()).equalsIgnoreCase(telefone2.getDdd().concat(telefone2.getNumero().toString()))) {
                        telefoneExiste = true;
                    }
                }
            }
        }

        if (!contatoExiste && !telefoneExiste) {
            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(arquivo))) {
                // Salvando contato no arquivo referente ao banco de dados
                contatos.add(contato);
                outputStream.writeObject(contatos);

                System.out.println("Novo contato salvo com sucesso!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Já existe um contato salvo com as informações fornecidas");
        }
    }

    private List<Contato> carregarContatos() {
        List<Contato> contatos = new ArrayList<>();

        if (new File(arquivo).exists()) { //Valida se arquivo existe
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(arquivo))) {
                contatos = (List<Contato>) inputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return contatos;
    }

    public void exibirContatos() {
        List<Contato> contatos = carregarContatos();
        for (Contato contato : contatos) {
            System.out.println(contato.toString());
        }
    }

    public void removerContato() {
        List<Contato> contatos = carregarContatos();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Insira o ID do contato: ");
        Long id = scanner.nextLong();

        for (Contato contato : contatos) {
            if (Objects.equals(contato.getId(), id)) {
                try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(arquivo))) {
                    // Salvando contato no arquivo referente ao banco de dados
                    contatos.remove(contato);
                    outputStream.writeObject(contatos);

                    System.out.println("Contato removido com sucesso!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    private List<Telefone> adicionarTelefone() {
        List<Telefone> telefones = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        int id;

        while (true) {
            System.out.print("Insira o ID do telefone (zero para encerrar): ");
            id = scanner.nextInt();

            if (id == 0) {
                break;
            }

            System.out.print("Insira o DDD do telefone: ");
            String ddd = scanner.next();

            System.out.print("Insira o numero do telefone: ");
            Long numero = scanner.nextLong();

            Telefone telefone = new Telefone((long) id, ddd, numero);
            telefones.add(telefone);
        }

        return telefones;
    }

    public void editarContato() {
        List<Contato> contatos = carregarContatos();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Insira o ID do contato: ");
        Long id = scanner.nextLong();

        for (Contato contato : contatos) {
            if (Objects.equals(contato.getId(), id)) {
                System.out.print("Insira o nome do contato: ");
                String nome = scanner.next();

                System.out.print("Insira o sobrenome do contato: ");
                String sobrenome = scanner.next();

                contato.setNome(nome);
                contato.setSobrenome(sobrenome);
                break;
            }
        }

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            outputStream.writeObject(contatos);

            System.out.println("Contato editado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
