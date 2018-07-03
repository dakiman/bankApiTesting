import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.Client;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import service.ClientService;
import utils.ValidationTestUtils;

public class ClientsTests {
    private static ClientService clientService;
    private static ValidationTestUtils validate;

    @BeforeSuite
    public static void setup() {
        ConfigBuilder.defaultSetup();
        clientService = new ClientService();
        validate = new ValidationTestUtils("clients");
    }

    @Test
    void shouldBeAbleToAddNewClient() {
        Client client = clientService.generateClient();
        Response res = clientService.addClient(client);
        Client newClient = res.getBody().as(Client.class);
        Assert.assertEquals(client, newClient);
    }

    @Test
    void nameIsRequired2() {
        Client client = clientService.generateClient();
        client.Name = null;
        Response res = clientService.addClient(client);
        Assert.assertTrue(res.statusCode() == 400);
        String msg = res.getBody().jsonPath().getString("ModelState.'clientModel.Name'[0]");
        Assert.assertEquals(msg, "The Name field is required.");
    }

    @Test
    void nameIsRequired() {
        validate.fieldIsRequired("Name", clientService.generateClient());
    }

    @Test
    void nameCantExceedLength() {
        validate.fieldMaximumLength("Name", 100, clientService.generateClient());
    }

    @Test
    void clientTypeIdIsRequired() {
        validate.fieldIsRequired("ClientTypeId", clientService.generateClient());
    }

    @Test
    void clientAddressIsRequired() {
        validate.fieldIsRequired("Address", clientService.generateClient());
    }

}
