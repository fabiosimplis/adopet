package br.com.alura.command.pet;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.command.Command;
import br.com.alura.command.CommandExecutor;
import br.com.alura.service.PetService;

import java.io.IOException;

public class ListarPetsCommand implements Command {


    @Override
    public void execute() {
        try {
            ClientHttpConfiguration client = new ClientHttpConfiguration();
            PetService petService = new PetService(client);
            petService.listarPetsDoAbrigo();
        } catch (IOException | InterruptedException e){
            System.err.println(e.getMessage());
        }
    }
}
