package br.com.alura.service;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.domain.Pet;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PetService {

    ClientHttpConfiguration client;

    public PetService(ClientHttpConfiguration client) {
        this.client = client;
    }

    public void listarPetsDoAbrigo() throws IOException, InterruptedException {
        System.out.println("Digite o id ou nome do abrigo:");
        String idOuNome = new Scanner(System.in).nextLine();

        String uri = "http://localhost:8080/abrigos/" +idOuNome +"/pets";
        HttpResponse<String> response =  client.dispararRequisicaoGet(uri);;
        int statusCode = response.statusCode();
        if (statusCode == 404 || statusCode == 500) {
            System.out.println("ID ou nome não cadastrado!");
            return;
        }
        String responseBody = response.body();
        Pet[] pets = new ObjectMapper().readValue(responseBody, Pet[].class);
        List<Pet> petsList = Arrays.stream(pets).toList();
        if (petsList.isEmpty()){
            System.out.println("Não há pets cadastrados");
        } else {
            System.out.println("Pets cadastrados:");
            Arrays.stream(pets).forEach(pet -> System.out.println(pet.getId()));
        }
    }

    public void importarPetsDoAbrigo() throws IOException, InterruptedException {

        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o id ou nome do abrigo:");
        String idOuNome = sc.nextLine();

        System.out.println("Digite o nome do arquivo CSV:");
        String nomeArquivo = sc.nextLine();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(nomeArquivo));
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo: " +nomeArquivo);

        }
        String line;
        while ((line = reader.readLine()) != null) {
            String[] campos = line.split(",");
            Pet pet = new Pet(campos[0], campos[1], campos[2], Integer.parseInt(campos[3]), campos[4], Float.parseFloat(campos[5]));


            String uri = "http://localhost:8080/abrigos/" + idOuNome + "/pets";
            System.out.println(pet);
            HttpResponse<String> response = client.dispararRequisicoesPost(uri, pet);
            int statusCode = response.statusCode();
            String responseBody = response.body();
            if (statusCode == 200) {
                System.out.println("Pet cadastrado com sucesso: " + pet.getNome());
            } else if (statusCode == 404) {
                System.out.println("Id ou nome do abrigo não encontado!");
                break;
            } else if (statusCode == 400 || statusCode == 500) {
                System.out.println("Erro ao cadastrar o pet: " + pet.getNome());
                System.out.println(responseBody);
                break;
            }
        }
        reader.close();
        //sc.close();

    }
}
