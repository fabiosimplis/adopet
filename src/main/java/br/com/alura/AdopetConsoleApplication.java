package br.com.alura;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.command.CommandExecutor;
import br.com.alura.command.abrigo.CadastrarAbrigoCommand;
import br.com.alura.command.abrigo.ListarAbrigoCommand;
import br.com.alura.command.pet.ImportarPetsDoAbrigoCommand;
import br.com.alura.service.AbrigoService;
import br.com.alura.service.PetService;

import java.util.Scanner;

public class AdopetConsoleApplication {

    public static void main(String[] args) {
        CommandExecutor executor = new CommandExecutor();
        ClientHttpConfiguration client = new ClientHttpConfiguration();
        AbrigoService abrigoService = new AbrigoService(client);
        PetService petService = new PetService(client);

        System.out.println("##### BOAS VINDAS AO SISTEMA ADOPET CONSOLE #####");
        try {
            int opcaoEscolhida = 0;
            while (opcaoEscolhida != 5) {
                exibirMenu();

                String textoDigitado = new Scanner(System.in).nextLine();
                opcaoEscolhida = Integer.parseInt(textoDigitado);

                switch (opcaoEscolhida){
                    case 1 -> executor.executeCommand(new ListarAbrigoCommand());
                    case 2 -> executor.executeCommand(new CadastrarAbrigoCommand());
                    case 3 -> executor.executeCommand(new ListarAbrigoCommand());
                    case 4 -> executor.executeCommand(new ImportarPetsDoAbrigoCommand());
                    case 5 -> System.exit(0);
                    default -> System.out.println("NÚMERO INVÁLIDO!");
                }

            }
            System.out.println("Finalizando o programa...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void exibirMenu(){
        System.out.println("\nDIGITE O NÚMERO DA OPERAÇÃO DESEJADA:");
        System.out.println("1 -> Listar abrigos cadastrados");
        System.out.println("2 -> Cadastrar novo abrigo");
        System.out.println("3 -> Listar pets do abrigo");
        System.out.println("4 -> Importar pets do abrigo");
        System.out.println("5 -> Sair");
    }
}
