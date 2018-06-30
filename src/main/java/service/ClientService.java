package service;

import client.RestClient;
import io.restassured.response.Response;
import models.Address;
import models.Client;
import utils.RandomUtils;

public class ClientService {
    RestClient restClient = new RestClient("clients/");

    public Response addClient(Client client) {
        return restClient.post(client);
    }

    private Address generateAddress() {
        Address address = new Address();
        address.Country = "Macedonia";
        address.City = "Skopje";
        address.Street = RandomUtils.randomString(15);

        return address;
    }

    public Client generateClient() {
        Client client = new Client();
        client.Name = RandomUtils.randomString();
        client.ClientTypeId = Long.parseLong(String.valueOf(RandomUtils.randomInt(1, 2)));
        client.IsActive = true;
        client.Address = generateAddress();

        return client;
    }
}
