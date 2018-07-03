package service;

import client.RestClient;
import io.restassured.response.Response;
import models.Account;

public class AccountService {
    RestClient restClient = new RestClient("accounts/");

    public Response addAccount(Account account) {
        return restClient.post(account);
    }

    public Response deleteAccount(Account account)
    {
        return restClient.delete(String.valueOf(account.Id));
    }

    public Account generateAccount(Integer clientId, Integer accountTypeId, String balance, String allowedDebt, Boolean isActive) {
        Account acc = new Account();
        acc.Id = 0;
        acc.ClientId = clientId;
        acc.AccountTypeId = accountTypeId;
        acc.Balance = balance;
        acc.AllowedDebt = allowedDebt;
        acc.IsActive = isActive;
        return acc;
    }

    public Account generateAndSaveAccount(Integer clientId, Integer accountTypeId, String balance, String allowedDebt, Boolean isActive)
    {
        Account acc = generateAccount(clientId, accountTypeId, balance, allowedDebt, isActive);
        return addAccount(acc).getBody().as(Account.class);
    }

}
