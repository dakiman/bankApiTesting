import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.restassured.response.Response;
import models.Account;
import models.Client;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import service.AccountService;
import service.ClientService;
import utils.AssertionUtils;
import utils.RandomUtils;
import utils.ValidationTestUtils;

import java.math.BigDecimal;

public class AccountsTests {
    private static ClientService clientService;
    private static AccountService accountService;
    private static ValidationTestUtils validate;

    @BeforeSuite
    public static void setup() {
        ConfigBuilder.defaultSetup();
        clientService = new ClientService();
        accountService = new AccountService();
        validate = new ValidationTestUtils("accounts");
    }

    @Test
    void shouldBeAbleToCreateNewDebitAccount() {
        Client client = clientService.generateAndAddClient();
        Account accountToBeAdded = accountService.generateAccount(client.Id, 1, RandomUtils.randomIntAsString(0,100000000), null, true);
        Response res = accountService.addAccount(accountToBeAdded);
        Account addedAccount = res.getBody().as(Account.class);
        Assert.assertEquals(res.statusCode(), 201);
        Assert.assertEquals(addedAccount, accountToBeAdded);
    }

    @Test
    void shouldBeAbleToCreateNewCreditAccountWithoutAllowedDebt()
    {
        Client client = clientService.generateAndAddClient();
        Account accountToBeAdded = accountService.generateAccount(client.Id, 2, RandomUtils.randomIntAsString(0,100000000), null, true);
        Response res = accountService.addAccount(accountToBeAdded);
        Account addedAccount = res.getBody().as(Account.class);
        Assert.assertEquals(res.statusCode(), 201);
        Assert.assertEquals(addedAccount, accountToBeAdded);
    }

    @Test
    void shouldNotBeAbleToCreateNewDebitAccountWithAllowedDebt()
    {
        Client client = clientService.generateAndAddClient();
        Account accountToBeAdded = accountService.generateAccount(client.Id, 1, RandomUtils.randomIntAsString(0,100000000), RandomUtils.randomIntAsString(0,10000), true);
        Response res = accountService.addAccount(accountToBeAdded);
        AssertionUtils.assertMessage(res, "Message", "Can't set allowed debt to debit account.", 400);
    }

    @Test
    void ShouldNotBeAbleToDeactivateAccountWithActiveBalance()
    {
        Client client = clientService.generateAndAddClient();
        Account account = accountService.generateAndSaveAccount(client.Id, 2, RandomUtils.randomIntAsString(0,100000000), RandomUtils.randomIntAsString(0,10000), true);
        Response res = accountService.deleteAccount(account);
        AssertionUtils.assertMessage(res, "Message", "Can't delete account with active balance", 400);
    }

    @Test
    void accountCanBeOneOfTwoTypes()
    {
        Client client = clientService.generateAndAddClient();
        Account accountToBeAdded = accountService.generateAndSaveAccount(client.Id, 1, RandomUtils.randomIntAsString(0,100000000), null, true);
        accountToBeAdded.AccountTypeId = RandomUtils.randomInt(3, 10);
        Response res = accountService.addAccount(accountToBeAdded);
        AssertionUtils.assertMessage(res, "ModelState.'accountModel.AccountTypeId'[0]", "AccountTypeId must be 1 or 2. 1 == Debit, 2 == Credit", 400);
    }

    @Test
    void clientIdIsRequired()
    {
        Account accountToBeAdded = accountService.generateAccount(0, 1, RandomUtils.randomIntAsString(0,100000000), null, true);
        validate.fieldIsRequired("ClientId", accountToBeAdded);
    }

    @Test
    void minimumAllowedDebt()
    {
        Account accountToBeAdded = accountService.generateAccount(0, 1, RandomUtils.randomIntAsString(0,100000000), null, true);
        validate.fieldMinimumValue("AllowedDebt", 0, 100000000, accountToBeAdded);
    }


}
