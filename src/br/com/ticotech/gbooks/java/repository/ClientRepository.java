package br.com.ticotech.gbooks.java.repository;

import br.com.ticotech.gbooks.java.entities.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClientRepository {
    private final List<Client> clientList = new ArrayList<>();;

    public ClientRepository(){
        clientList.add(new Client("12312312311", 60));
        clientList.add(new Client("12312312322", 0));
        clientList.add(new Client("12312312333", 20));
    }

    public List<Client> getClientList() {
        return clientList;
    }
    public Client getClient(String cpf){
        for (Client client: clientList){
            if (Objects.equals(client.getCpf(), cpf)){
                return client;
            }
        }
        return null;
    }
    public void addClient(Client client){
        clientList.add(client);
    }
}
