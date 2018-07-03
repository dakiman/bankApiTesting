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
        address.Id = 0;
        address.Country = "Macedonia";
        address.City = "Skopje";
        address.Street = RandomUtils.randomString(15);

        return address;
    }

    public Client generateClient() {
        Client client = new Client();
        client.Id = 0;
        client.Name = RandomUtils.randomString();
        client.ClientTypeId = Long.valueOf(RandomUtils.randomInt(1, 2));
        client.IsActive = true;
        client.Address = generateAddress();

        return client;
    }

    public Client generateAndAddClient() {
        Client client = generateClient();
        return addClient(client).getBody().as(Client.class);
    }
}
