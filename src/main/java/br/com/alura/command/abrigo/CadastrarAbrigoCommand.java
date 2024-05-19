package br.com.alura.command.abrigo;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.command.Command;
import br.com.alura.service.AbrigoService;

import java.io.IOException;

public class CadastrarAbrigoCommand implements Command {
    @Override
    public void execute() {
        try {
            ClientHttpConfiguration client = new ClientHttpConfiguration();
            AbrigoService abrigoService = new AbrigoService(client);
            abrigoService.cadastrarAbrigo();
        } catch (IOException | InterruptedException e){
            System.err.println(e.getMessage());
        }

    }
}
