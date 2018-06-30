package service;

import client.RestClient;
import io.restassured.response.Response;
import models.Account;
import models.Transaction;

class AccountService {
    RestClient restClient = new RestClient("accounts/");

    public Response addAccount(Account transaction) {
        return restClient.post(transaction);
    }
}
