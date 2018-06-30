import io.restassured.response.Response;
import models.Client;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import service.ClientService;

public class BasicTest {
    private static ClientService clientService;

    @BeforeSuite
    public static void setup() {
        ConfigBuilder.defaultSetup();
        clientService = new ClientService();
    }

    @Test
    void doShit() {
        Client client = clientService.generateClient();
        Response res = clientService.addClient(client);
        Client newClient = res.getBody().as(Client.class);
        Assert.assertEquals(client, newClient);
    }
}
